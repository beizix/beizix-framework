package app.module.admin.usecase.exboard.save.adapters.web;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.exboard.save.adapters.web.model.ExBoardCreateBindingVO;
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
