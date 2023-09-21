package org.beizix.admin.feature.role.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.utility.common.MessageUtil;
import org.beizix.security.application.port.in.role.RoleRemoveService;

@RestController
@RequiredArgsConstructor
class AdminUserRoleRemoveController {
  private final RoleRemoveService roleRemoveService;
  private final MessageUtil messageUtil;

  @PostMapping("/api/adminRole/remove")
  ResponseEntity<?> remove(AdminUserRoleDto formDto) {
    roleRemoveService.operate(formDto.getRole());
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.builder()
                .message(messageUtil.getMessage("operation.common.remove.done"))
                .build());
  }
}
