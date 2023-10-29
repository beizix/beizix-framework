package org.beizix.admin.adapter.web.uri;

import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.save.URIInput;
import org.beizix.core.application.port.in.uri.URISavePortIn;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.core.common.util.CoreUtil;
import org.beizix.core.config.exception.AlreadyExistItemException;
import org.beizix.core.config.exception.UnAcceptableFileException;
import org.beizix.utility.common.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class URICreateUpdateController {
  private final CoreUtil coreUtil;
  private final URISavePortIn uriSavePortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/api/uri/save")
  public ResponseEntity<?> save(@Valid URIDto formDto, BindingResult bindingResult)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    try {
      uriSavePortIn.connect(
          new URIInput(
              formDto.getId(),
              formDto.getParentId(),
              formDto.getAppType(),
              formDto.getUri(),
              formDto.isShowOnNavi(),
              formDto.getText(),
              formDto.getOrderNo(),
              formDto.getOgTitle(),
              formDto.getOgDesc(),
              formDto.getOgKeywords(),
              formDto.getOgImage(),
              formDto.getRoles()),
          formDto.getOgImageFile(),
          "create".equals(formDto.getMode()));

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
                .message(messageUtil.getMessage("operation.common.save.done"))
                .build());
  }
}
