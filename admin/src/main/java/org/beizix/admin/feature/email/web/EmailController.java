package org.beizix.admin.feature.email.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class EmailController {

  @GetMapping(path = "/addons/email/form")
  public String emailForm(@ModelAttribute("formDto") EmailDto formDto) {
    return "email/emailForm";
  }
}
