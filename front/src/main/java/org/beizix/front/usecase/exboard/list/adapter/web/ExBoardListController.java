package org.beizix.front.usecase.exboard.list.adapter.web;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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

  @GetMapping(path = "/board/exampleBoard")
  String list(
      HttpServletRequest request,
      Model model,
      @PageableDefault(sort = ExBoard_.ORDER_NO, direction = Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") ExBoardListFilterVO filterVO) {

    Page<ExBoardElement> listOutput =
        exBoardListPortIn.connect(
            pageable,
            new ExBoardListFilterCommand(
                filterVO.getSearchField(), filterVO.getSearchValue(), filterVO.getSearchOpen()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "board/exampleBoardList";
  }
}
