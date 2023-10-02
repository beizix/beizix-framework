package org.beizix.admin.adapter.web.exboard;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.adapter.web.exboard.model.save.ExBoardSaveAttachFormVO;
import org.beizix.admin.adapter.web.exboard.model.save.ExBoardSaveFormVO;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveOutput;
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
class ExBoardSaveController {
  private final ExBoardSavePortIn exBoardSavePortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;
  private final ExBoardViewPortIn exBoardViewPortIn;

  @PostMapping(path = {"/board/exampleBoard/create", "/board/exampleBoard/update/{id}"})
  String operate(
      RedirectAttributes redirectAttributes,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO,
      @Valid @ModelAttribute("formVO") ExBoardSaveFormVO saveReqVO,
      BindingResult bindingResult)
      throws IOException {

    // 수정일 경우, 대표 이미지와 비공개 첨부 정보는 전달받지 않기에 기존 저장된 정보를 조회해서 가져온다.
    if (saveReqVO.getId() != null) {
      Optional.of(exBoardViewPortIn.connect(saveReqVO.getId()))
          .ifPresent(
              viewOutput -> {
                saveReqVO.setRepresentImage(viewOutput.getRepresentImage());
                saveReqVO.setPrivateAttachment(viewOutput.getPrivateAttachment());
                saveReqVO.setAttachments(
                    viewOutput.getAttachments().stream()
                        .map(
                            attachment ->
                                modelMapper.map(attachment, ExBoardSaveAttachFormVO.class))
                        .collect(Collectors.toList()));
              });
    }

    if (bindingResult.hasErrors()) {
      return "board/exBoardForm";
    }

    try {
      ExBoardSaveOutput createdItem =
          exBoardSavePortIn.connect(
              modelMapper.map(saveReqVO, ExBoardSaveInput.class),
              saveReqVO.getRepresentImgFile(),
              saveReqVO.getMultipartPrivateAttachment(),
              saveReqVO.getMultipartAttachments());

      redirectAttributes.addFlashAttribute(
          "operationMessage",
          messageUtil.getMessage(
              saveReqVO.getId() != null
                  ? "operation.board.exampleBoard.updated"
                  : "operation.board.exampleBoard.created",
              createdItem.getTitle()));

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
      return "board/exBoardForm";
    }

    return "redirect:/board/exampleBoard";
  }
}
