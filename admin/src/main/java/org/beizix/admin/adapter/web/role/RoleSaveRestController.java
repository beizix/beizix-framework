package org.beizix.admin.adapter.web.role;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.role.model.save.RoleBindingVO;
import org.beizix.core.configuration.adapter.web.rest.RestResponse;
import org.beizix.core.configuration.application.util.CoreUtil;
import org.beizix.admin.usecase.role.save.application.port.in.RoleSavePortIn;
import org.beizix.security.config.exceptions.AlreadyExistsRoleException;
import org.beizix.utility.common.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RoleSaveRestController {
  private final CoreUtil coreUtil;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;
  private final RoleSavePortIn roleSavePortIn;

  @PostMapping("/api/adminRole/save")
  ResponseEntity<?> operate(@Valid RoleBindingVO bindingVO, BindingResult bindingResult) {
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
