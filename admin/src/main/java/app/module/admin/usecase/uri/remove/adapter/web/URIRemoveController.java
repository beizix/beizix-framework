package app.module.admin.usecase.uri.remove.adapter.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uri.remove.application.port.in.URIRemovePortIn;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.core.config.application.util.CoreUtil;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.exception.NonRemovableItemException;
import app.module.core.config.application.util.MessageUtil;
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
