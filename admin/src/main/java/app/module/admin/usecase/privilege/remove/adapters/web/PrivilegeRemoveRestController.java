package app.module.admin.usecase.privilege.remove.adapters.web;

import app.module.admin.usecase.privilege.remove.adapters.web.model.RoleRemoveVO;
import app.module.admin.usecase.privilege.remove.ports.PrivilegeRemovePortIn;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.core.config.application.util.MessageUtil;
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
