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
import org.beizix.admin.adapter.web.admin.model.filter.AdminListReqParam;
import org.beizix.admin.adapter.web.admin.model.save.AdminSaveReqVO;
import org.beizix.admin.adapter.web.admin.validator.AdminUpdateValidator;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReferInput;
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveInput;
import org.beizix.security.application.domain.role.model.save.RoleSaveReferInput;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
class AdminUpdateController {
  private final AdminUpdateValidator adminUpdateValidator;
  private final RoleListPortIn roleListPortIn;
  private final AdminSavePortIn adminSavePortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/settings/admins/update/{id}")
  String operate(
      Model model,
      RedirectAttributes redirectAttributes,
      @ModelAttribute("paramDto") AdminListReqParam paramDto,
      @Valid @ModelAttribute("formDto") AdminSaveReqVO formDto,
      BindingResult bindingResult) {

    adminUpdateValidator.validate(formDto, bindingResult);
    if (bindingResult.hasErrors()) {
      formDto.setEditMode(true);
      model.addAttribute("roles", roleListPortIn.connect());
      return "admin/adminForm";
    }

    AdminSaveInput adminDto = modelMapper.map(formDto, AdminSaveInput.class);
    adminDto.setWithRoles(
        formDto.getRoleIds().stream()
            .map(
                roleId ->
                    AdminWithRoleSaveInput.builder()
                        .admin(new AdminSaveReferInput(formDto.getId()))
                        .role(new RoleSaveReferInput(roleId))
                        .build())
            .collect(Collectors.toSet()));

    adminSavePortIn.connect(adminDto);

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage("operation.settings.admin.updated", formDto.getId()));

    return "redirect:/settings/admins";
  }
}
