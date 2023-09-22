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
import org.beizix.security.application.domain.admin.model.save.AdminSaveReferInput;
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveInput;
import org.beizix.security.application.domain.role.model.save.RoleSaveReferInput;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
public class AdminCreateController {
  private final RoleListPortIn roleListPortIn;
  private final MessageUtil messageUtil;
  private final AdminSavePortIn savePortIn;
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
      model.addAttribute("roles", roleListPortIn.connect());
      return "admin/adminForm";
    }

    AdminSaveInput admin = modelMapper.map(formDto, AdminSaveInput.class);
    admin.setWithRoles(
        formDto.getRoleIds().stream()
            .map(
                roleId ->
                    AdminWithRoleSaveInput.builder()
                        .admin(new AdminSaveReferInput(formDto.getId()))
                        .role(new RoleSaveReferInput(roleId))
                        .build())
            .collect(Collectors.toSet()));

    savePortIn.connect(admin);

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage("operation.settings.admin.created", formDto.getId()));

    return "redirect:/settings/admins";
  }
}
