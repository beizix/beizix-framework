package org.beizix.admin.adapter.web.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.role.model.remove.RoleRemoveReqVO;
import org.beizix.core.configuration.adapter.web.rest.RestResponse;
import org.beizix.security.application.port.in.privilege.PrivilegeRemovePortIn;
import org.beizix.utility.common.MessageUtil;
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
  ResponseEntity<?> remove(RoleRemoveReqVO formDto) {
    removePortIn.connect(formDto.getId());
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(messageUtil.getMessage("operation.common.remove.done"))
                .build());
  }
}
