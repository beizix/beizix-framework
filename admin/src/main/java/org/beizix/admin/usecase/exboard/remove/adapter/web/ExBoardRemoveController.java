package org.beizix.admin.usecase.exboard.remove.adapter.web;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.usecase.exboard.remove.application.port.in.ExBoardRemovePortIn;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
class ExBoardRemoveController {
  private final ExBoardRemovePortIn exBoardRemovePortIn;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/board/exampleBoard/delete")
  String operate(RedirectAttributes redirectAttributes, ExBoardRemoveVO removeReqVO) {

    if (CollectionUtils.isNotEmpty(removeReqVO.getSelectedItemIds())) {
      exBoardRemovePortIn.connect(removeReqVO.getSelectedItemIds());
      redirectAttributes.addFlashAttribute(
          "operationMessage", messageUtil.getMessage("operation.board.exampleBoard.removed"));
    }

    return "redirect:/board/exampleBoard";
  }
}
