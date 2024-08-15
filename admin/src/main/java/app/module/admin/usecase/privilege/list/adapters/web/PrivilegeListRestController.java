package app.module.admin.usecase.privilege.list.adapters.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.list.adapters.web.model.PrivilegeElementVO;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.admin.usecase.privilege.list.ports.application.domain.PrivilegeElement;
import app.module.admin.usecase.privilege.list.ports.PrivilegeListPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PrivilegeListRestController {
  private final PrivilegeListPortIn<PrivilegeElement> listPortIn;

  @GetMapping("/api/privilege/get/recursiveItems")
  ResponseEntity<?> operate() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.<PrivilegeElementVO>builder()
                .items(
                    listPortIn.connect().stream()
                        .map(
                            p ->
                                new PrivilegeElementVO(
                                    p.getCreatedBy(),
                                    p.getCreatedAt(),
                                    p.getUpdatedBy(),
                                    p.getUpdatedAt(),
                                    p.getId(),
                                    p.getDescription(),
                                    p.getOrderNo()))
                        .collect(Collectors.toList()))
                .build());
  }
}
