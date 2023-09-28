package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListInput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class ExBoardListController {
  private final ExBoardListPortIn exBoardListPortIn;
  private final ModelMapper modelMapper;

  @GetMapping("/board/exampleBoard")
  String operate(
      Model model,
      @PageableDefault(sort = "orderNo", direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO) {

    filterReqVO.setSize(pageable.getPageSize());

    model.addAttribute(
        "items",
        exBoardListPortIn.connect(pageable, modelMapper.map(filterReqVO, ExBoardListInput.class)));

    // title, seo 속성 변경 예제
    //    URI currentURI = (URI) request.getAttribute("currentURI");
    //    currentURI.setOgTitle("modified - notice title");
    //    currentURI.setOgKeywords("modified - notice keywords");
    //    currentURI.setOgDesc("modified - description");
    //    model.addAttribute("currentURI", currentURI);

    return "board/exampleBoardList";
  }
}
