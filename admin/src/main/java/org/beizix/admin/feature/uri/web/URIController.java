package org.beizix.admin.feature.uri.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.beizix.core.config.enums.AppType;
import org.beizix.security.application.port.in.role.RoleListPortIn;

@Controller
@RequiredArgsConstructor
@Slf4j
class URIController {
  private final RoleListPortIn roleListPortIn;

  @GetMapping(path = "/settings/uri/{appType}")
  String intro(Model model, @PathVariable AppType appType) {
    model.addAttribute("roles", roleListPortIn.connect());
    model.addAttribute("appType", appType);
    return "uri/uriList";
  }
}
