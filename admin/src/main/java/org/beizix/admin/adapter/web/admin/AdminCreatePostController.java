package org.beizix.admin.adapter.web.admin;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.admin.model.create.AdminCreateBindingVO;
import org.beizix.admin.adapter.web.admin.validator.AdminCreateValidator;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.beizix.utility.common.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AdminCreatePostController {
  private final RoleListPortIn roleListPortIn;
  private final MessageUtil messageUtil;
  private final AdminSavePortIn savePortIn;
  private final ModelMapper modelMapper;
  private final AdminCreateValidator adminCreateValidator;

  @PostMapping(path = "/settings/admins/create")
  String operate(
      RedirectAttributes redirectAttributes,
      Model model,
      @Valid @ModelAttribute("bindingVO") AdminCreateBindingVO bindingVO,
      BindingResult bindingResult) {

    adminCreateValidator.validate(bindingVO, bindingResult);
    if (bindingResult.hasErrors()) {
      model.addAttribute("roles", roleListPortIn.connect());
      return "admin/adminCreateForm";
    }

    savePortIn.connect(
        bindingVO.getId(),
        bindingVO.getPassword(),
        bindingVO.getEmail(),
        bindingVO.getAccountDisabled(),
        bindingVO.getAccountLocked(),
        bindingVO.getRoleIds());

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage("operation.settings.admin.created", bindingVO.getId()));

    return "redirect:/settings/admins";
  }
}
