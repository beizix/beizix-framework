package org.beizix.admin.adapter.web.admin;

import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.beizix.admin.adapter.web.admin.model.save.AdminSaveReqVO;
import org.beizix.admin.adapter.web.admin.validator.AdminCreateValidator;
import org.beizix.security.application.domain.admin.model.save.AdminSaveMinimumReq;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveReq;
import org.beizix.security.application.domain.role.model.save.RoleSaveMinimumReq;
import org.beizix.security.application.port.in.admin.AdminSaveService;
import org.beizix.security.application.port.in.role.RoleListService;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
public class AdminCreateController {
  private final RoleListService roleListService;
  private final MessageUtil messageUtil;
  private final AdminSaveService adminSaveService;
  private final ModelMapper modelMapper;
  private final AdminCreateValidator adminCreateValidator;

  @PostMapping(path = "/settings/admins/create")
  String operate(
      RedirectAttributes redirectAttributes,
      Model model,
      @Valid @ModelAttribute("formDto") AdminSaveReqVO formDto,
      BindingResult bindingResult) {

    adminCreateValidator.validate(formDto, bindingResult);
    if (bindingResult.hasErrors()) {
      model.addAttribute("roles", roleListService.operate());
      return "admin/adminForm";
    }

    AdminSaveReq admin = modelMapper.map(formDto, AdminSaveReq.class);
    admin.setWithRoles(
        formDto.getRoleIds().stream()
            .map(
                roleId ->
                    AdminWithRoleSaveReq.builder()
                        .admin(new AdminSaveMinimumReq(formDto.getId()))
                        .role(new RoleSaveMinimumReq(roleId))
                        .build())
            .collect(Collectors.toSet()));

    adminSaveService.operate(admin);

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage("operation.settings.admin.created", formDto.getId()));

    return "redirect:/settings/admins";
  }
}
