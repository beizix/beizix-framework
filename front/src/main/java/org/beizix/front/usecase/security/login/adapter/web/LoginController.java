package org.beizix.front.usecase.security.login.adapter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
  @GetMapping("/login")
  public String login() {
    return "security/login";
  }
}
