package org.beizix.admin.usecase.role.remove.adapter.web;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.remove.adapter.web.RoleRemoveVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.configuration.adapter.web.rest.RestResponse;
import org.beizix.utility.common.MessageUtil;
import org.beizix.admin.usecase.role.remove.application.port.in.RoleRemovePortIn;

@RestController
@RequiredArgsConstructor
class RoleRemoveRestController {
  private final RoleRemovePortIn roleRemovePortIn;
  private final MessageUtil messageUtil;

  @PostMapping("/api/adminRole/remove")
  ResponseEntity<?> remove(RoleRemoveVO formDto) {
    roleRemovePortIn.connect(formDto.getId());
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(messageUtil.getMessage("operation.common.remove.done"))
                .build());
  }
}
