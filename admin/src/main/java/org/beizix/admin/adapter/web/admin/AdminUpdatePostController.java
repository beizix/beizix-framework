package org.beizix.admin.adapter.web.admin;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.admin.model.filter.AdminListStatusVO;
import org.beizix.admin.adapter.web.admin.model.update.AdminUpdateBindingVO;
import org.beizix.admin.adapter.web.admin.validator.AdminUpdateValidator;
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
class AdminUpdatePostController {
  private final AdminUpdateValidator adminUpdateValidator;
  private final RoleListPortIn roleListPortIn;
  private final AdminSavePortIn adminSavePortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/settings/admins/update/{id}")
  String operate(
      Model model,
      RedirectAttributes redirectAttributes,
      @ModelAttribute("listStatus") AdminListStatusVO listStatus,
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
