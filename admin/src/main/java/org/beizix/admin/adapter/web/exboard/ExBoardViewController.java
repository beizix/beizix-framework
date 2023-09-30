package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.adapter.web.exboard.model.view.ExBoardViewRespVO;
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
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO) {

    model.addAttribute(
        "viewRespVO",
        id == null
            ? new ExBoardViewRespVO()
            : modelMapper.map(exBoardViewPortIn.operate(id), ExBoardViewRespVO.class));

    model.addAttribute("editMode", true);

    return "board/exBoardView";
  }
}
