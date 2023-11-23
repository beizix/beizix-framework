package org.beizix.admin.usecases.uicode.index.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class UICodeIndexController {

  @GetMapping("/settings/uicode")
  String process() {
    return "uiCode/uiCodeList";
  }
}
