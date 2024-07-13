package org.beizix.admin.usecase.exboard.list.adapters.web;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.list.adapters.web.model.ExBoardListFilterVO;
import org.beizix.core.config.adapter.persistence.entity.ExBoard_;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardElement;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.port.in.ExBoardListPortIn;
import org.beizix.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class ExBoardListController {
  private final ExBoardListPortIn exBoardListPortIn;
  private final UICodeListPortIn uiCodeListPortIn;

  @GetMapping("/board/exampleBoard")
  String operate(
      Model model,
      @PageableDefault(sort = ExBoard_.ORDER_NO, direction = Direction.DESC) Pageable pageable,
      @ModelAttribute("filterReqVO") ExBoardListFilterVO filterVO) {

    Page<ExBoardElement> listOutput =
        exBoardListPortIn.connect(
            pageable,
            new ExBoardListFilterCommand(
                filterVO.getSearchField(), filterVO.getSearchValue(), filterVO.getSearchOpen()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    // title, seo 속성 변경 예제
    //    URI currentURI = (URI) request.getAttribute("currentURI");
    //    currentURI.setOgTitle("modified - notice title");
    //    currentURI.setOgKeywords("modified - notice keywords");
    //    currentURI.setOgDesc("modified - description");
    //    model.addAttribute("currentURI", currentURI);

    return "board/exBoardList";
  }
}
