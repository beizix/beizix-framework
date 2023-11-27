package org.beizix.admin.usecase.privilege.index.adapter.web;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.beizix.core.configuration.application.enums.AppType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/settings/adminPrivilege")
@Slf4j
class PrivilegeIndexController {
  @GetMapping
  String intro(Model model) {
    model.addAttribute("appType", AppType.ADMIN);
    return "privilege/privilegeList";
  }
}
