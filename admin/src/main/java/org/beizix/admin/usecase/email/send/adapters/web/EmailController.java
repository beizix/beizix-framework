package org.beizix.admin.usecase.email.send.adapters.web;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.email.send.adapters.web.model.EmailVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class EmailController {

  @GetMapping(path = "/addons/email/form")
  public String emailForm(@ModelAttribute("formDto") EmailVO emailVO) {
    return "email/emailForm";
  }
}
