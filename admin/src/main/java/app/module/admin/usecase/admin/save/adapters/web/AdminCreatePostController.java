package app.module.admin.usecase.admin.save.adapters.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.save.adapters.web.model.AdminCreateBindingVO;
import app.module.admin.usecase.admin.save.adapters.web.validator.AdminCreateValidator;
import app.module.admin.usecase.admin.save.ports.AdminSavePortIn;
import app.module.admin.usecase.role.list.ports.RoleListPortIn;
import app.module.core.config.application.util.MessageUtil;
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
