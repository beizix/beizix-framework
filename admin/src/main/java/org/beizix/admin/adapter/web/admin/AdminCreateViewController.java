package org.beizix.admin.adapter.web.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.beizix.admin.adapter.web.admin.model.view.AdminViewRespVO;
import org.beizix.security.application.port.in.role.RoleListPortIn;

@Controller
@RequiredArgsConstructor
public class AdminCreateViewController {
  private final RoleListPortIn roleListPortIn;

  @GetMapping(path = "/settings/admins/create")
  String operate(Model model, @ModelAttribute("formDto") AdminViewRespVO formDto) {
    model.addAttribute("roles", roleListPortIn.connect());
    return "admin/adminForm";
  }
}
