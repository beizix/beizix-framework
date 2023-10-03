package org.beizix.admin.adapter.web.exboard;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.create.ExBoardCreateReqVO;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
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
class ExBoardCreatePostController {
  private final ExBoardSavePortIn exBoardSavePortIn;
  private final MessageUtil messageUtil;

  @PostMapping(path = {"/board/exampleBoard/create"})
  String operate(
      RedirectAttributes redirectAttributes,
      @ModelAttribute("filterReqVO") ExBoardListFilterReqVO filterReqVO,
      @Valid @ModelAttribute("formVO") ExBoardCreateReqVO createReqVO,
      BindingResult bindingResult,
      MultipartFile representImgFile, // 대표 이미지 파일
      List<MultipartFile> multipartAttachments, // 다건 첨부 파일 목록
      MultipartFile multipartPrivateAttachment, // Private 첨부 파일
      Model model)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return "board/exBoardCreateForm";
    }

    try {
      exBoardSavePortIn.connect(
          new ExBoardSaveInput(
              null,
              createReqVO.getTitle(),
              createReqVO.getContent(),
              createReqVO.getVisible(),
              createReqVO.getBoardStartDate(),
              createReqVO.getBoardEndDate(),
              null,
              createReqVO.getRepImgAlt(),
              null,
              null,
              null,
              createReqVO.getOrderNo()),
          representImgFile,
          multipartPrivateAttachment,
          multipartAttachments);

      redirectAttributes.addFlashAttribute(
          "operationMessage",
          messageUtil.getMessage("operation.board.exampleBoard.created", createReqVO.getTitle()));

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
      return "board/exBoardCreateForm";
    }

    return "redirect:/board/exampleBoard";
  }
}
