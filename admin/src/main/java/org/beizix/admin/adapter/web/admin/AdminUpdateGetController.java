package org.beizix.admin.adapter.web.admin;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.admin.model.filter.AdminListStatusVO;
import org.beizix.admin.adapter.web.admin.model.update.AdminUpdateBindingVO;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
class AdminUpdateGetController {
  private final AdminViewPortIn adminViewPortIn;
  private final RoleListPortIn roleListPortIn;
  private final MessageUtil messageUtil;

  @GetMapping(path = "/settings/admins/update/{adminId}")
  String operate(
      Model model,
      @PathVariable String adminId,
      @ModelAttribute("pageable") final PageableInput pageableInput,
      @ModelAttribute("listStatus") AdminListStatusVO listStatus) {

    AdminViewOutput output =
        adminViewPortIn
            .connect(adminId)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", adminId, "Admin ID")));

    AdminUpdateBindingVO bindingVO =
        new AdminUpdateBindingVO(
            output.getCreatedBy(),
            output.getCreatedAt(),
            output.getUpdatedBy(),
            output.getUpdatedAt(),
            output.getId(),
            null,
            output.getEmail(),
            output.getPasswordUpdatedAt(),
            output.getAccountDisabled(),
            output.getLoginFailCnt(),
            output.getAccountLocked(),
            output.getWithRoles().stream()
                .map(w -> w.getRole().getId())
                .collect(Collectors.toList()));

    model.addAttribute("bindingVO", bindingVO);
    model.addAttribute("roles", roleListPortIn.connect());

    return "admin/adminUpdateForm";
  }
}
