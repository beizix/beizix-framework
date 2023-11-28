package org.beizix.front.usecase.exboard.list.adapter.web;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.ExBoard_;
import org.beizix.core.config.application.aop.PageDefault;
import org.beizix.core.config.application.component.PageableInput;
import org.beizix.core.config.application.enums.OrderDir;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardPageableList;
import org.beizix.core.usecase.exboard.list.application.port.in.ExBoardListPortIn;
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
      @ModelAttribute("paramDto") ExBoardListFilterVO filterVO) {
    ExBoardPageableList listOutput =
        exBoardListPortIn.connect(
            pageableInput,
            new ExBoardListFilterCommand(
                filterVO.getSearchField(), filterVO.getSearchValue(), filterVO.getSearchOpen()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "board/exampleBoardList";
  }
}
