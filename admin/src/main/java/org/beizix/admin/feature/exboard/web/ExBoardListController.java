package org.beizix.admin.feature.exboard.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.beizix.admin.feature.exboard.web.model.ExBoardDto;
import org.beizix.admin.feature.exboard.web.model.ExBoardListConditionDto;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.exboard.application.model.ExBoardListCondition;
import org.beizix.core.feature.exboard.application.service.ExBoardListService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
class ExBoardListController {
  private final ExBoardListService exBoardListService;
  private final ModelMapper modelMapper;

  @GetMapping("/board/exampleBoard")
  String operate(
      HttpServletRequest request,
      Model model,
      @PageableDefault(sort = "orderNo", direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") ExBoardListConditionDto paramDto) {

    paramDto.setSize(pageable.getPageSize());

    model.addAttribute(
        "items",
        exBoardListService
            .operate(pageable, modelMapper.map(paramDto, ExBoardListCondition.class))
            .map(exBoard -> modelMapper.map(exBoard, ExBoardDto.class)));

    // title, seo 속성 변경 예제
    //    URI currentURI = (URI) request.getAttribute("currentURI");
    //    currentURI.setOgTitle("modified - notice title");
    //    currentURI.setOgKeywords("modified - notice keywords");
    //    currentURI.setOgDesc("modified - description");
    //    model.addAttribute("currentURI", currentURI);

    return "board/exampleBoardList";
  }
}
