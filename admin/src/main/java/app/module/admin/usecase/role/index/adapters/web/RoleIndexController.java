package app.module.admin.usecase.role.index.adapters.web;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.list.ports.application.domain.PrivilegeElement;
import app.module.admin.usecase.privilege.list.ports.PrivilegeListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
class RoleIndexController {
  private final PrivilegeListPortIn<PrivilegeElement> privilegeListPortIn;

  @GetMapping("/settings/adminRoles")
  String intro(Model model) {
    model.addAttribute("privileges", privilegeListPortIn.connect());
    return "adminRole/adminRoleList";
  }
}
