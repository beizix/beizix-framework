package org.beizix.admin.feature.home.web;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.beizix.security.application.port.in.admin.AdminViewService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
  private final AdminViewService adminViewService;

  @GetMapping
  public String home(Principal principal, Model model) {
    adminViewService
        .operate(principal.getName())
        .ifPresent(
            loginUser -> {
              model.addAttribute("loginUser", loginUser);
              model.addAttribute("loginUserRoles", loginUser.getWithRoles());
            });

    return "home";
  }
}
