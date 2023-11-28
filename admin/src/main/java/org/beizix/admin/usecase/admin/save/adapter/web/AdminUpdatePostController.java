package org.beizix.admin.usecase.admin.save.adapter.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.list.adapter.web.AdminListFilterVO;
import org.beizix.admin.usecase.admin.save.adapter.web.validator.AdminUpdateValidator;
import org.beizix.admin.usecase.admin.save.application.port.in.AdminSavePortIn;
import org.beizix.admin.usecase.role.list.application.port.in.RoleListPortIn;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
class AdminUpdatePostController {
  private final AdminUpdateValidator adminUpdateValidator;
  private final RoleListPortIn roleListPortIn;
  private final AdminSavePortIn adminSavePortIn;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/settings/admins/update/{id}")
  String operate(
      Model model,
      RedirectAttributes redirectAttributes,
      @ModelAttribute("listStatus") AdminListFilterVO listStatus,
      @Valid @ModelAttribute("bindingVO") AdminUpdateBindingVO bindingVO,
      BindingResult bindingResult) {

    adminUpdateValidator.validate(bindingVO, bindingResult);
    if (bindingResult.hasErrors()) {
      model.addAttribute("roles", roleListPortIn.connect());
      return "admin/adminUpdateForm";
    }

    adminSavePortIn.connect(
        bindingVO.getId(),
        bindingVO.getUpdatePassword(),
        bindingVO.getEmail(),
        bindingVO.getAccountDisabled(),
        bindingVO.getAccountLocked(),
        bindingVO.getRoleIds());

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage("operation.settings.admin.updated", bindingVO.getId()));

    return "redirect:/settings/admins";
  }
}
