package org.beizix.admin.adapter.web.uicode;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/settings/uicode")
@Slf4j
class UICodeController {
  @GetMapping
  String process() {
    return "uiCode/uiCodeList";
  }
}
