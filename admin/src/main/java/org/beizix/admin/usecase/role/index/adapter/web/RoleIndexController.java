package org.beizix.admin.usecase.role.index.adapter.web;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.list.application.domain.PrivilegeElement;
import org.beizix.admin.usecase.privilege.list.application.port.in.PrivilegeListPortIn;
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
