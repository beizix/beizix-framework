package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.beizix.admin.adapter.web.exboard.model.ExBoardDto;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.port.in.exboard.ExBoardSavePortIn;
import org.beizix.utility.common.MessageUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
class ExBoardSaveController {
  private final ExBoardSavePortIn exBoardSavePortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  @PostMapping(path = {"/board/exampleBoard/create", "/board/exampleBoard/update/{id}"})
  String operate(
      RedirectAttributes redirectAttributes,
      Principal principal,
      @Valid @ModelAttribute("dto") ExBoardDto dto,
      BindingResult bindingResult)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return "board/exampleBoardForm";
    }

    dto.setUpdatedBy(principal.getName());
    if (dto.getId() == null) dto.setCreatedBy(principal.getName());

    ExBoardInput createdItem = exBoardSavePortIn.connect(modelMapper.map(dto, ExBoardInput.class));

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage(
            dto.getId() != null
                ? "operation.board.exampleBoard.updated"
                : "operation.board.exampleBoard.created",
            createdItem.getTitle()));

    return "redirect:/board/exampleBoard";
  }
}
