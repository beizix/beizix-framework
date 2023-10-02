package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.adapter.web.exboard.model.save.ExBoardSaveFormVO;
import org.beizix.core.application.domain.common.model.PageableBase;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewOutput;
import org.beizix.core.application.port.in.exboard.ExBoardViewPortIn;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
class ExBoardViewController {
  private final ExBoardViewPortIn exBoardViewPortIn;
  private final ModelMapper modelMapper;

  @GetMapping(path = {"/board/exampleBoard/create", "/board/exampleBoard/update/{id}"})
  String operate(
      Model model,
      @PathVariable(required = false) Long id,
      @ModelAttribute("pageable") PageableBase pageableBase,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO) {

    ExBoardViewOutput viewOutput =
        id != null ? exBoardViewPortIn.connect(id) : new ExBoardViewOutput();

    model.addAttribute("formVO", modelMapper.map(viewOutput, ExBoardSaveFormVO.class));

    return "board/exBoardForm";
  }
}
