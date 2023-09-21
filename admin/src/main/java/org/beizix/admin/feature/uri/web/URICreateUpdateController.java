package org.beizix.admin.feature.uri.web;

import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.core.common.util.CoreUtil;
import org.beizix.core.config.exception.AlreadyExistItemException;
import org.beizix.core.config.exception.UnAcceptableFileException;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URICreateUpdateService;
import org.beizix.utility.common.MessageUtil;

@RestController
@RequiredArgsConstructor
public class URICreateUpdateController {
  private final CoreUtil coreUtil;
  private final URICreateUpdateService uriCreateUpdateService;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/api/uri/save")
  public ResponseEntity<?> save(@Valid URIDto formDto, BindingResult bindingResult)
      throws IOException {

    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    URI item;
    try {
      item =
          uriCreateUpdateService.operate(
              modelMapper.map(formDto, URI.class), "create".equals(formDto.getMode()));

    } catch (UnAcceptableFileException e) {
      bindingResult.rejectValue("ogImageFile", "", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);

    } catch (AlreadyExistItemException e) {
      bindingResult.rejectValue("uri", "", new String[] {"URI"}, e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.builder()
                .item(modelMapper.map(item, URIDto.class))
                .message(messageUtil.getMessage("operation.common.save.done"))
                .build());
  }
}
