package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.config.aop.PageDefault;
import org.beizix.core.application.domain.common.model.PageableBase;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.beizix.core.config.enums.OrderDir;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class ExBoardListController {
  private final ExBoardListPortIn exBoardListPortIn;

  @GetMapping("/board/exampleBoard")
  String operate(
      Model model,
      @PageDefault(orderBy = "orderNo", orderDir = OrderDir.DESC) PageableBase pageableBase,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO) {

    ExBoardListOutput listOutput =
        exBoardListPortIn.connect(
            pageableBase,
            new ExBoardListFilterInput(
                filterReqVO.getSearchField(),
                filterReqVO.getSearchValue(),
                filterReqVO.getSearchOpen()));

    model.addAttribute("listOutput", listOutput);

    // title, seo 속성 변경 예제
    //    URI currentURI = (URI) request.getAttribute("currentURI");
    //    currentURI.setOgTitle("modified - notice title");
    //    currentURI.setOgKeywords("modified - notice keywords");
    //    currentURI.setOgDesc("modified - description");
    //    model.addAttribute("currentURI", currentURI);

    return "board/exBoardList";
  }
}
