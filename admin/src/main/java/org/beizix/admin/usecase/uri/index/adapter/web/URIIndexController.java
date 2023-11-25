package org.beizix.admin.usecase.uri.index.adapter.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.config.enums.AppType;
import org.beizix.security.application.domain.role.model.list.RoleOutput;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
class URIIndexController {
  private final RoleListPortIn<RoleOutput> roleListPortIn;

  @GetMapping(path = "/settings/uri/{appType}")
  String intro(Model model, @PathVariable AppType appType) {
    model.addAttribute("roles", roleListPortIn.connect());
    model.addAttribute("appType", appType);
    return "uri/uriList";
  }
}
