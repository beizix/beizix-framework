package org.beizix.admin.adapter.web.exboard;

import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.adapter.web.exboard.model.save.ExBoardSaveReqVO;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveOutput;
import org.beizix.core.application.port.in.exboard.ExBoardSavePortIn;
import org.beizix.core.config.enums.FileUploadType;
import org.beizix.core.config.exception.UnAcceptableFileException;
import org.beizix.utility.common.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO,
      @Valid @ModelAttribute("viewRespVO") ExBoardSaveReqVO saveReqVO,
      BindingResult bindingResult)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return "board/exBoardView";
    }

    ExBoardSaveOutput createdItem;

    try {
      createdItem = exBoardSavePortIn.connect(modelMapper.map(saveReqVO, ExBoardSaveInput.class));
    } catch (UnAcceptableFileException ex) {
      switch (ex.getFileUploadType()) {
        case EXAMPLE_REP:
          bindingResult.rejectValue("representImgFile", "", ex.getMessage());
          break;
        case EXAMPLE_PUBLIC:
          bindingResult.rejectValue("multipartAttachments", "", ex.getMessage());
          break;
        case EXAMPLE_PRIVATE:
          bindingResult.rejectValue("multipartPrivateAttachment", "", ex.getMessage());
          break;
      }
      return "board/exBoardView";
    }

    redirectAttributes.addFlashAttribute(
        "operationMessage",
        messageUtil.getMessage(
            saveReqVO.getId() != null
                ? "operation.board.exampleBoard.updated"
                : "operation.board.exampleBoard.created",
            createdItem != null ? createdItem.getTitle() : ""));

    return "redirect:/board/exampleBoard";
  }
}
