package org.beizix.admin.adapter.web.admin;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.beizix.admin.adapter.web.admin.model.filter.AdminListReqParam;
import org.beizix.admin.adapter.web.admin.model.view.AdminViewRespVO;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
class AdminUpdateViewController {
  private final AdminViewPortIn adminViewPortIn;
  private final ModelMapper modelMapper;
  private final RoleListPortIn roleListPortIn;
  private final MessageUtil messageUtil;

  @GetMapping(path = "/settings/admins/update/{adminId}")
  String operate(
      Model model,
      @PathVariable String adminId,
      @ModelAttribute("paramDto") AdminListReqParam paramDto) {

    AdminViewOutput item =
        adminViewPortIn
            .connect(adminId)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", adminId, "Admin ID")));

    AdminViewRespVO formDto = modelMapper.map(item, AdminViewRespVO.class);
    formDto.setEditMode(true);
    formDto.setRoleIds(
        item.getWithRoles().stream()
            .map(adminUserWithRole -> adminUserWithRole.getRole().getId())
            .collect(Collectors.toSet()));

    model.addAttribute("formDto", formDto);
    model.addAttribute("roles", roleListPortIn.connect());

    return "admin/adminForm";
  }
}
