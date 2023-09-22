package org.beizix.admin.adapter.web.role;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.beizix.core.config.enums.AppType;

@Controller
@RequiredArgsConstructor
@RequestMapping("/settings/adminRoles")
@Slf4j
class RoleIndexController {
  @GetMapping
  String intro(Model model) {
    model.addAttribute("appType", AppType.ADMIN);
    return "adminRole/adminRoleList";
  }
}
