package org.beizix.front.feature.exboard.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.beizix.core.feature.exboard.application.model.ExBoardListCondition;
import org.beizix.core.feature.exboard.application.service.ExBoardListService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
class ExBoardListController {
  private final ExBoardListService exBoardListService;
  private final ModelMapper modelMapper;

  @GetMapping(path = "/board/exampleBoard")
  String list(
      HttpServletRequest request,
      Model model,
      @PageableDefault(size = 5, sort = "updatedAt", direction = Sort.Direction.DESC)
          Pageable pageable,
      @ModelAttribute("paramDto") ExBoardListConditionDto paramDto) {
    paramDto.setSize(pageable.getPageSize());

    model.addAttribute(
        "items",
        exBoardListService.operate(pageable, modelMapper.map(paramDto, ExBoardListCondition.class)));

    return "board/exampleBoardList";
  }
}
