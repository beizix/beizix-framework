package org.beizix.admin.usecase.exboard.save.adapter.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.adapter.web.exboard.model.update.ExBoardUpdateAttachVO;
import org.beizix.admin.adapter.web.exboard.model.update.ExBoardBindingVO;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.usecase.exboard.view.domain.ExBoardView;
import org.beizix.core.usecase.exboard.view.application.port.in.ExBoardViewPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
class ExBoardUpdateGetController {
  private final ExBoardViewPortIn<ExBoardView> exBoardViewPortIn;

  @GetMapping(path = {"/board/exampleBoard/update/{id}"})
  String operate(
      Model model,
      @PathVariable(required = false) final Long id,
      @ModelAttribute("pageable") final PageableInput pageableInput,
      @ModelAttribute("filterReqVO") final ExBoardListFilterReqVO filterReqVO) {

    ExBoardView output = exBoardViewPortIn.connect(id);

    model.addAttribute(
        "bindingVO",
        new ExBoardBindingVO(
            output.getCreatedBy(),
            output.getCreatedAt(),
            output.getUpdatedBy(),
            output.getUpdatedAt(),
            output.getId(),
            output.getVisible(),
            output.getTitle(),
            output.getContent(),
            output.getBoardStartDate(),
            output.getBoardEndDate(),
            output.getRepresentImage(),
            output.getAttachments().stream()
                .map(at -> new ExBoardUpdateAttachVO(at.getId(), at.getFileUploadOutput()))
                .collect(Collectors.toList()),
            output.getPrivateAttachment(),
            null,
            output.getRepImgAlt(),
            output.getOrderNo()));

    return "board/exBoardUpdateForm";
  }
}
