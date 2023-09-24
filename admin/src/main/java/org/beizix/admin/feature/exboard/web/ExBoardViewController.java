package org.beizix.admin.feature.exboard.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.beizix.admin.feature.exboard.web.model.ExBoardDto;
import org.beizix.admin.feature.exboard.web.model.ExBoardListConditionDto;
import org.beizix.core.application.port.in.exboard.ExBoardViewPortIn;

@Controller
@RequiredArgsConstructor
class ExBoardViewController {
  private final ExBoardViewPortIn exBoardViewPortIn;
  private final ModelMapper modelMapper;

  @GetMapping(path = {"/board/exampleBoard/create", "/board/exampleBoard/update/{id}"})
  String operate(
      Model model,
      @PathVariable(required = false) Long id,
      @ModelAttribute("paramDto") ExBoardListConditionDto paramDto) {

    model.addAttribute(
        "dto",
        id == null
            ? ExBoardDto.builder().build()
            : modelMapper.map(exBoardViewPortIn.operate(id), ExBoardDto.class));

    model.addAttribute("editMode", true);

    return "board/exampleBoardForm";
  }
}
