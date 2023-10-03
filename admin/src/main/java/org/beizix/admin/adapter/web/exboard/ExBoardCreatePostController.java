package org.beizix.admin.adapter.web.exboard;

import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.create.ExBoardCreateReqVO;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.port.in.exboard.ExBoardSavePortIn;
import org.beizix.core.application.port.in.exboard.ExBoardViewPortIn;
import org.beizix.core.config.exception.UnAcceptableFileException;
import org.beizix.utility.common.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
class ExBoardCreatePostController {
  private final ExBoardSavePortIn exBoardSavePortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;
  private final ExBoardViewPortIn exBoardViewPortIn;

  @PostMapping(path = {"/board/exampleBoard/create"})
  String operate(
      RedirectAttributes redirectAttributes,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO,
      @Valid @ModelAttribute("formVO") ExBoardCreateReqVO createReqVO,
      BindingResult bindingResult)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return "board/exBoardCreateForm";
    }

    try {
      exBoardSavePortIn.connect(
          modelMapper.map(createReqVO, ExBoardSaveInput.class),
          createReqVO.getRepresentImgFile(),
          createReqVO.getMultipartPrivateAttachment(),
          createReqVO.getMultipartAttachments());

      redirectAttributes.addFlashAttribute(
          "operationMessage",
          messageUtil.getMessage("operation.board.exampleBoard.created", createReqVO.getTitle()));

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
      return "board/exBoardCreateForm";
    }

    return "redirect:/board/exampleBoard";
  }
}
