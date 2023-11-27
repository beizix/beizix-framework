package org.beizix.admin.usecase.uri.index.adapter.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import org.beizix.admin.usecase.role.list.application.domain.RoleElement;
import org.beizix.admin.usecase.role.list.application.port.in.RoleListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
class URIIndexController {
  private final RoleListPortIn<RoleElement> roleListPortIn;
  private final UICodeListPortIn uiCodeListPortIn;

  @GetMapping(path = "/settings/uri/{appType}")
  String intro(Model model, @PathVariable AppType appType) {
    model.addAttribute("roles", roleListPortIn.connect());
    model.addAttribute("appType", appType);
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "uri/uriList";
  }
}
