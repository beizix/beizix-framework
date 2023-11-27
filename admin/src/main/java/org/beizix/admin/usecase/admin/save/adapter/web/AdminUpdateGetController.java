package org.beizix.admin.usecase.admin.save.adapter.web;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.list.adapter.web.AdminListFilterVO;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.admin.usecase.admin.view.application.domain.AdminView;
import org.beizix.admin.usecase.admin.view.application.domain.RoleView;
import org.beizix.admin.usecase.admin.view.application.port.in.AdminViewPortIn;
import org.beizix.admin.usecase.role.list.application.port.in.RoleListPortIn;
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
      @ModelAttribute("listStatus") AdminListFilterVO listStatus) {

    AdminView output =
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
            output.getRoles().stream().map(RoleView::getId).collect(Collectors.toList()));

    model.addAttribute("bindingVO", bindingVO);
    model.addAttribute("roles", roleListPortIn.connect());

    return "admin/adminUpdateForm";
  }
}
