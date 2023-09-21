package org.beizix.admin.feature.uri.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.core.common.util.CoreUtil;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.exception.NonRemovableItemException;
import org.beizix.core.feature.uri.application.service.URIRemoveService;
import org.beizix.utility.common.MessageUtil;

@RestController
@RequiredArgsConstructor
public class URIRemoveController {
  private final CoreUtil coreUtil;
  private final MessageUtil messageUtil;
  private final URIRemoveService uriRemoveService;

  @PostMapping(path = "/api/uri/remove")
  ResponseEntity<?> remove(@Valid URIRemoveDto formDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    try {
      uriRemoveService.operate(AppType.ADMIN, formDto.getId());
    } catch (NonRemovableItemException e) {
      bindingResult.reject("", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.builder()
                .message(messageUtil.getMessage("operation.common.remove.done"))
                .build());
  }
}
