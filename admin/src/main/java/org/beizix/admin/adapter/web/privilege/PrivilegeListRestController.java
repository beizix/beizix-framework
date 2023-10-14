package org.beizix.admin.adapter.web.privilege;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.privilege.model.list.PrivilegeBindingVO;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.security.application.domain.privilege.model.list.PrivilegeOutput;
import org.beizix.security.application.port.in.privilege.PrivilegeListPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PrivilegeListRestController {
  private final PrivilegeListPortIn<PrivilegeOutput> listPortIn;

  @GetMapping("/api/privilege/get/recursiveItems")
  ResponseEntity<?> operate() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.<PrivilegeBindingVO>builder()
                .items(
                    listPortIn.connect().stream()
                        .map(
                            p ->
                                new PrivilegeBindingVO(
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
