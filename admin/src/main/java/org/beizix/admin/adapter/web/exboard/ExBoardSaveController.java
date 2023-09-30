package org.beizix.admin.adapter.web.exboard;

import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.save.ExBoardSaveReqVO;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.port.in.exboard.ExBoardSavePortIn;
import org.beizix.utility.common.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
class ExBoardSaveController {
  private final ExBoardSavePortIn exBoardSavePortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  @PostMapping(path = {"/board/exampleBoard/create", "/board/exampleBoard/update/{id}"})
  String operate(
      RedirectAttributes redirectAttributes,
      @Valid @ModelAttribute("dto") ExBoardSaveReqVO vo,
      BindingResult bindingResult)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return "board/exBoardView";
    }

    ExBoardSaveInput createdItem =
        exBoardSavePortIn.connect(modelMapper.map(vo, ExBoardSaveInput.class));

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage(
            vo.getId() != null
                ? "operation.board.exampleBoard.updated"
                : "operation.board.exampleBoard.created",
            createdItem.getTitle()));

    return "redirect:/board/exampleBoard";
  }
}
