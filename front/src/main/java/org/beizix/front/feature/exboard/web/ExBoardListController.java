package org.beizix.front.feature.exboard.web;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard_;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.usecase.exboard.list.domain.ExBoardPageableList;
import org.beizix.core.usecase.exboard.list.application.port.in.ExBoardListPortIn;
import org.beizix.core.config.aop.PageDefault;
import org.beizix.core.config.enums.OrderDir;
import org.beizix.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class ExBoardListController {

  private final ExBoardListPortIn exBoardListPortIn;
  private final UICodeListPortIn uiCodeListPortIn;

  @GetMapping(path = "/board/exampleBoard")
  String list(
      HttpServletRequest request,
      Model model,
      @PageDefault(orderBy = ExBoard_.ORDER_NO, orderDir = OrderDir.DESC)
          PageableInput pageableInput,
      @ModelAttribute("paramDto") ExBoardListConditionDto paramDto) {
    ExBoardPageableList listOutput =
        exBoardListPortIn.connect(
            pageableInput,
            new ExBoardListFilterInput(
                paramDto.getSearchField(), paramDto.getSearchValue(), paramDto.getSearchOpen()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "board/exampleBoardList";
  }
}
