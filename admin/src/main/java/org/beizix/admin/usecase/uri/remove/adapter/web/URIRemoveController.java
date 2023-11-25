package org.beizix.admin.usecase.uri.remove.adapter.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uri.remove.application.port.in.URIRemovePortIn;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.core.common.util.CoreUtil;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.exception.NonRemovableItemException;
import org.beizix.utility.common.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class URIRemoveController {
  private final CoreUtil coreUtil;
  private final MessageUtil messageUtil;
  private final URIRemovePortIn uriRemovePortIn;

  @PostMapping(path = "/api/uri/remove")
  ResponseEntity<?> remove(@Valid URIRemoveVO removeVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    try {
      uriRemovePortIn.connect(AppType.ADMIN, removeVO.getId());
    } catch (NonRemovableItemException e) {
      bindingResult.reject("", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(messageUtil.getMessage("operation.common.remove.done"))
                .build());
  }
}
