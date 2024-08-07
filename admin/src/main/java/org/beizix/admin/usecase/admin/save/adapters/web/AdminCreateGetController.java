package org.beizix.admin.usecase.admin.save.adapters.web;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.save.adapters.web.model.AdminCreateBindingVO;
import org.beizix.admin.usecase.role.list.ports.RoleListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class AdminCreateGetController {
  private final RoleListPortIn roleListPortIn;

  @GetMapping(path = "/settings/admins/create")
  String operate(Model model, @ModelAttribute("bindingVO") AdminCreateBindingVO bindingVO) {
    model.addAttribute("roles", roleListPortIn.connect());
    return "admin/adminCreateForm";
  }
}
