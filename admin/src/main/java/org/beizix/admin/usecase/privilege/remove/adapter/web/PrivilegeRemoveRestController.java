package org.beizix.admin.usecase.privilege.remove.adapter.web;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.web.rest.response.RestResponse;
import org.beizix.admin.usecase.privilege.remove.application.port.in.PrivilegeRemovePortIn;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PrivilegeRemoveRestController {
  private final PrivilegeRemovePortIn removePortIn;
  private final MessageUtil messageUtil;

  @PostMapping("/api/adminPrivilege/remove")
  ResponseEntity<?> remove(RoleRemoveVO formDto) {
    removePortIn.connect(formDto.getId());
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(messageUtil.getMessage("operation.common.remove.done"))
                .build());
  }
}
