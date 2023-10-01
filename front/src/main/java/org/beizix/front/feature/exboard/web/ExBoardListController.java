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
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
class ExBoardListController {
  private final ExBoardListPortIn exBoardListPortIn;
  private final ModelMapper modelMapper;

  @GetMapping(path = "/board/exampleBoard")
  String list(
      HttpServletRequest request,
      Model model,
      @PageableDefault(size = 5, sort = "updatedAt", direction = Sort.Direction.DESC)
          Pageable pageable,
      @ModelAttribute("paramDto") ExBoardListConditionDto paramDto) {
    paramDto.setSize(pageable.getPageSize());

//    model.addAttribute(
//        "items",
//        exBoardListPortIn.connect(pageable, modelMapper.map(paramDto, ExBoardListFilterInput.class)));

    return "board/exampleBoardList";
  }
}
