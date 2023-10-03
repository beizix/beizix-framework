package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.create.ExBoardCreateReqVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExBoardCreateGetController {

  @GetMapping(path = {"/board/exampleBoard/create"})
  String operate(Model model) {

    model.addAttribute("formVO", new ExBoardCreateReqVO());
    return "board/exBoardCreateForm";
  }
}
