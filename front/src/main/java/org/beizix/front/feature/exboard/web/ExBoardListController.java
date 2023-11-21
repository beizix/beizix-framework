package org.beizix.front.feature.exboard.web;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard_;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.beizix.core.config.aop.PageDefault;
import org.beizix.core.config.enums.OrderDir;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class ExBoardListController {

  private final ExBoardListPortIn exBoardListPortIn;
  private final ModelMapper modelMapper;

  @GetMapping(path = "/board/exampleBoard")
  String list(
      HttpServletRequest request,
      Model model,
      @PageDefault(orderBy = ExBoard_.ORDER_NO, orderDir = OrderDir.DESC)
      PageableInput pageableInput,
      @ModelAttribute("paramDto") ExBoardListConditionDto paramDto
  ) {
    ExBoardListOutput listOutput =
        exBoardListPortIn.connect(
            pageableInput,
            new ExBoardListFilterInput(
                paramDto.getSearchField(),
                paramDto.getSearchValue(),
                paramDto.getSearchOpen()));

    model.addAttribute("listOutput", listOutput);

    return "board/exampleBoardList";
  }
}
