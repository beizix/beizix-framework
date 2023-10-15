package org.beizix.admin.adapter.web.role;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.role.model.save.RoleSaveReqVO;
import org.beizix.admin.adapter.web.role.model.save.RoleSaveRespVO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.core.common.util.CoreUtil;
import org.beizix.security.application.domain.role.model.save.RoleSaveInput;
import org.beizix.security.application.port.in.role.RoleSavePortIn;
import org.beizix.security.config.exceptions.AlreadyExistsRoleException;
import org.beizix.utility.common.MessageUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/adminRole")
class RoleSaveRestController {
  private final CoreUtil coreUtil;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;
  private final RoleSavePortIn roleSavePortIn;

  @PostMapping("save")
  ResponseEntity<?> operate(@Valid RoleSaveReqVO saveReqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    RoleSaveInput roleDto;
    try {
      roleDto =
          roleSavePortIn.connect(modelMapper.map(saveReqVO, RoleSaveInput.class));

    } catch (AlreadyExistsRoleException e) {
      bindingResult.rejectValue("id", "", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(modelMapper.map(roleDto, RoleSaveRespVO.class))
                .message(messageUtil.getMessage("operation.common.save.done"))
                .build());
  }
}
