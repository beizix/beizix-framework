package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.core.application.port.in.exboard.ExBoardRemovePortIn;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
class ExBoardRemoveController {
  private final ExBoardRemovePortIn exBoardRemovePortIn;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/board/exampleBoard/delete")
  String operate(
      RedirectAttributes redirectAttributes, @ModelAttribute("paramDto") ExBoardListFilterReqVO paramDto) {

    if (paramDto.getItemIds() != null && paramDto.getItemIds().size() > 0) {
      exBoardRemovePortIn.connect(paramDto.getItemIds());
      redirectAttributes.addFlashAttribute(
          "operationMessage", messageUtil.getMessage("operation.board.exampleBoard.removed"));
    }

    return "redirect:/board/exampleBoard";
  }
}
