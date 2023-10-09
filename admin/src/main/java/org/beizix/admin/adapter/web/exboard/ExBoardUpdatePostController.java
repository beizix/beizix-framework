package org.beizix.admin.adapter.web.exboard;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.adapter.web.exboard.model.update.ExBoardUpdateReqVO;
import org.beizix.core.application.port.in.exboard.ExBoardSavePortIn;
import org.beizix.core.config.exception.UnAcceptableFileException;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
class ExBoardUpdatePostController {
  private final ExBoardSavePortIn exBoardSavePortIn;
  private final MessageUtil messageUtil;

  @PostMapping(path = {"/board/exampleBoard/update/{id}"})
  String operate(
      RedirectAttributes redirectAttributes,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO,
      @Valid @ModelAttribute("formVO") ExBoardUpdateReqVO updateReqVO,
      BindingResult bindingResult,
      MultipartFile representImgFile, // 대표 이미지 파일
      List<MultipartFile> multipartAttachments, // 다건 첨부 파일 목록
      MultipartFile multipartPrivateAttachment, // Private 첨부 파일
      Model model)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return "board/exBoardUpdateForm";
    }

    try {
      exBoardSavePortIn.connect(
          updateReqVO.getId(),
          updateReqVO.getTitle(),
          updateReqVO.getContent(),
          updateReqVO.getVisible(),
          updateReqVO.getBoardStartDate(),
          updateReqVO.getBoardEndDate(),
          updateReqVO.getRepImgAlt(),
          updateReqVO.getOrderNo(),
          updateReqVO.getRemoveAttachmentIds(),
          representImgFile,
          multipartPrivateAttachment,
          multipartAttachments);

      redirectAttributes.addFlashAttribute(
          "operationMessage",
          messageUtil.getMessage("operation.board.exampleBoard.updated", updateReqVO.getTitle()));

    } catch (UnAcceptableFileException ex) {
      switch (ex.getFileUploadType()) {
        case EXAMPLE_REP:
          model.addAttribute("repImgErrorMsg", ex.getMessage());
          break;
        case EXAMPLE_PUBLIC:
          model.addAttribute("publicAttachErrorMsg", ex.getMessage());
          break;
        case EXAMPLE_PRIVATE:
          model.addAttribute("privateAttachErrorMsg", ex.getMessage());
          break;
      }
      return "board/exBoardUpdateForm";
    }

    return "redirect:/board/exampleBoard";
  }
}
