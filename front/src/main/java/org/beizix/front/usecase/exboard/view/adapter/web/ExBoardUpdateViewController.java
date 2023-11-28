package org.beizix.front.usecase.exboard.view.adapter.web;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.exboard.view.application.domain.ExBoardView;
import org.beizix.front.usecase.exboard.list.adapter.web.ExBoardListFilterVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.beizix.core.usecase.exboard.view.application.port.in.ExBoardViewPortIn;

@Controller
@RequiredArgsConstructor
public class ExBoardUpdateViewController {
  private final ExBoardViewPortIn<ExBoardView> exBoardViewPortIn;
  private final ModelMapper modelMapper;

  @GetMapping(path = "/board/exampleBoard/update/{id}")
  public String updateGet(
      Model model, @PathVariable long id, @ModelAttribute("paramDto") ExBoardListFilterVO paramDto) {
    ExBoardVO exBoardVO = modelMapper.map(exBoardViewPortIn.connect(id), ExBoardVO.class);
    exBoardVO.setEditMode(true);
    model.addAttribute("formDto", exBoardVO);

    return "board/exampleBoardForm";
  }
}
