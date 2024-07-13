package org.beizix.admin.usecase.privilege.save.adapter.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.web.rest.response.RestResponse;
import org.beizix.core.config.application.util.CoreUtil;
import org.beizix.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;
import org.beizix.admin.usecase.privilege.save.application.port.in.PrivilegeSavePortIn;
import org.beizix.admin.config.application.exception.AlreadyExistsRoleException;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PrivilegeSaveRestController {
  private final CoreUtil coreUtil;
  private final MessageUtil messageUtil;
  private final PrivilegeSavePortIn savePortIn;

  @PostMapping("/api/adminPrivilege/save")
  ResponseEntity<?> operate(@Valid PrivilegeSaveBindingVO bindingVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    PrivilegeSaveCommand saveInput =
        new PrivilegeSaveCommand(
            bindingVO.getId(), bindingVO.getDescription(), bindingVO.getOrderNo());

    try {
      savePortIn.connect(saveInput);

      // `create` 인 경우 save 과정에서 orderNo 가 생성된다.
      bindingVO.setOrderNo(saveInput.getOrderNo());

    } catch (AlreadyExistsRoleException e) {
      bindingResult.rejectValue("id", "", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(bindingVO)
                .message(messageUtil.getMessage("operation.common.save.done"))
                .build());
  }
}
