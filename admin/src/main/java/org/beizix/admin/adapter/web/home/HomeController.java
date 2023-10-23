package org.beizix.admin.adapter.web.home;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
  private final AdminViewPortIn adminViewPortIn;

  @GetMapping
  public String home(Principal principal, Model model) {
    adminViewPortIn
        .connect(principal.getName())
        .ifPresent(
            loginUser -> {
              model.addAttribute("loginUser", loginUser);
              model.addAttribute("loginUserRoles", loginUser.getRoles());
            });

    return "home";
  }
}
