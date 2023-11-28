package org.beizix.admin.usecase.exboard.save.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExBoardCreateGetController {

  @GetMapping(path = {"/board/exampleBoard/create"})
  String operate(Model model) {

    model.addAttribute("bindingVO", new ExBoardCreateBindingVO());
    return "board/exBoardCreateForm";
  }
}
