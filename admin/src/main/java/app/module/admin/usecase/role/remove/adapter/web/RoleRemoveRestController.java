package app.module.admin.usecase.role.remove.adapter.web;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.remove.adapters.web.model.RoleRemoveVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.core.config.application.util.MessageUtil;
import app.module.admin.usecase.role.remove.application.port.in.RoleRemovePortIn;

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
