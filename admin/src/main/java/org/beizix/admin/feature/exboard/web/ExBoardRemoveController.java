package org.beizix.admin.feature.exboard.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.beizix.admin.feature.exboard.web.model.ExBoardListConditionDto;
import org.beizix.core.feature.exboard.application.service.ExBoardRemoveService;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
class ExBoardRemoveController {
  private final ExBoardRemoveService exBoardRemoveService;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/board/exampleBoard/delete")
  String operate(
      RedirectAttributes redirectAttributes, @ModelAttribute("paramDto") ExBoardListConditionDto paramDto) {

    if (paramDto.getItemIds() != null && paramDto.getItemIds().size() > 0) {
      exBoardRemoveService.operate(paramDto.getItemIds());
      redirectAttributes.addFlashAttribute(
          "operationMessage", messageUtil.getMessage("operation.board.exampleBoard.removed"));
    }

    return "redirect:/board/exampleBoard";
  }
}
