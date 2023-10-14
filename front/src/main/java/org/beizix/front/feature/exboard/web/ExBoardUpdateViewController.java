package org.beizix.front.feature.exboard.web;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewOutput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.beizix.core.application.port.in.exboard.ExBoardViewPortIn;

@Controller
@RequiredArgsConstructor
public class ExBoardUpdateViewController {
  private final ExBoardViewPortIn<ExBoardViewOutput> exBoardViewPortIn;
  private final ModelMapper modelMapper;

  @GetMapping(path = "/board/exampleBoard/update/{id}")
  public String updateGet(
      Model model, @PathVariable long id, @ModelAttribute("paramDto") ExBoardListConditionDto paramDto) {
    ExBoardDto exBoardDto = modelMapper.map(exBoardViewPortIn.connect(id), ExBoardDto.class);
    exBoardDto.setEditMode(true);
    model.addAttribute("formDto", exBoardDto);

    return "board/exampleBoardForm";
  }
}
