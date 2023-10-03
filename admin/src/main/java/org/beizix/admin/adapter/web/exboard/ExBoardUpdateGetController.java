package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.adapter.web.exboard.model.update.ExBoardUpdateReqVO;
import org.beizix.core.application.domain.common.model.PageableInput;
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
class ExBoardUpdateGetController {
  private final ExBoardViewPortIn exBoardViewPortIn;
  private final ModelMapper modelMapper;

  @GetMapping(path = {"/board/exampleBoard/update/{id}"})
  String operate(
      Model model,
      @PathVariable(required = false) Long id,
      @ModelAttribute("pageable") PageableInput pageableInput,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO) {

    ExBoardViewOutput viewOutput = exBoardViewPortIn.connect(id);

    model.addAttribute("formVO", modelMapper.map(viewOutput, ExBoardUpdateReqVO.class));

    return "board/exBoardUpdateForm";
  }
}
