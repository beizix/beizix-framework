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
import org.beizix.admin.adapter.web.admin.model.query.AdminListReqParam;
import org.beizix.admin.adapter.web.admin.model.view.AdminViewRespVO;
import org.beizix.security.application.port.in.role.RoleListService;
import org.beizix.security.application.domain.admin.model.view.AdminViewResp;
import org.beizix.security.application.port.in.admin.AdminViewService;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
class AdminUpdateViewController {
  private final AdminViewService adminViewService;
  private final ModelMapper modelMapper;
  private final RoleListService roleListService;
  private final MessageUtil messageUtil;

  @GetMapping(path = "/settings/admins/update/{adminId}")
  String operate(
      Model model,
      @PathVariable String adminId,
      @ModelAttribute("paramDto") AdminListReqParam paramDto) {

    AdminViewResp item =
        adminViewService
            .operate(adminId)
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
    model.addAttribute("roles", roleListService.operate());

    return "admin/adminForm";
  }
}
