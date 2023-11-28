package org.beizix.admin.usecase.uri.save.adapter.web;

import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uri.save.application.domain.URISaveCommand;
import org.beizix.admin.usecase.uri.save.application.port.in.URISavePortIn;
import org.beizix.core.config.adapter.web.rest.RestResponse;
import org.beizix.core.config.application.enums.SaveType;
import org.beizix.core.config.application.exception.AlreadyExistItemException;
import org.beizix.core.config.application.exception.UnAcceptableFileException;
import org.beizix.core.config.application.util.CoreUtil;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class URISaveController {
  private final CoreUtil coreUtil;
  private final URISavePortIn uriSavePortIn;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/api/uri/save")
  public ResponseEntity<?> save(@Valid URISaveBindingVO saveVO, BindingResult bindingResult)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    String createdId;
    try {
      createdId =
          uriSavePortIn.connect(
              new URISaveCommand(
                  saveVO.getId(),
                  saveVO.getParentId(),
                  saveVO.getAppType(),
                  saveVO.getUri(),
                  saveVO.isShowOnNavi(),
                  saveVO.getText(),
                  saveVO.getOrderNo(),
                  saveVO.getOgTitle(),
                  saveVO.getOgDesc(),
                  saveVO.getOgKeywords(),
                  saveVO.getOgImage(),
                  saveVO.getRoles()),
              saveVO.getOgImageFile(),
              saveVO.getSaveType().equals(SaveType.CREATE));

    } catch (UnAcceptableFileException e) {
      bindingResult.rejectValue("ogImageFile", "", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);

    } catch (AlreadyExistItemException e) {
      bindingResult.rejectValue("uri", "", new String[] {"URI"}, e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(createdId)
                .message(messageUtil.getMessage("operation.common.save.done"))
                .build());
  }
}
