package org.beizix.admin.usecase.role.save.adapter.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.application.exception.AlreadyExistsRoleException;
import org.beizix.admin.usecase.role.save.application.port.in.RoleSavePortIn;
import org.beizix.core.config.adapter.web.rest.response.RestResponse;
import org.beizix.core.config.application.util.CoreUtil;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RoleSaveRestController {
  private final CoreUtil coreUtil;
  private final MessageUtil messageUtil;
  private final RoleSavePortIn roleSavePortIn;

  @PostMapping("/api/adminRole/save")
  ResponseEntity<?> operate(@Valid RoleSaveBindingVO bindingVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    try {
      roleSavePortIn.connect(
          bindingVO.getId(),
          bindingVO.getDescription(),
          bindingVO.getOrderNo(),
          bindingVO.getPrivilegeIds());

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
