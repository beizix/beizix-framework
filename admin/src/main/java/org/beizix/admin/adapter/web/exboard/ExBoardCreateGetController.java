package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.create.ExBoardCreateRespVO;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.core.application.domain.common.model.PageableBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class ExBoardCreateGetController {

  @GetMapping(path = {"/board/exampleBoard/create"})
  String operate(
      Model model,
      @ModelAttribute("pageable") PageableBase pageableBase,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO) {

    model.addAttribute("formVO", new ExBoardCreateRespVO());
    return "board/exBoardCreateForm";
  }
}
