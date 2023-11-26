package org.beizix.admin.adapter.web.privilege;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.privilege.model.save.PrivilegeBindingVO;
import org.beizix.core.configuration.adapter.web.rest.RestResponse;
import org.beizix.core.configuration.application.util.CoreUtil;
import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;
import org.beizix.security.application.port.in.privilege.PrivilegeSavePortIn;
import org.beizix.security.config.exceptions.AlreadyExistsRoleException;
import org.beizix.utility.common.MessageUtil;
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
  ResponseEntity<?> operate(@Valid PrivilegeBindingVO bindingVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    PrivilegeSaveInput saveInput =
        new PrivilegeSaveInput(
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
