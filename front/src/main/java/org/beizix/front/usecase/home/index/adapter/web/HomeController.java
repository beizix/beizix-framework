package org.beizix.front.usecase.home.index.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
  @GetMapping(path = "/")
  public String home() {
    return "home";
  }
}
