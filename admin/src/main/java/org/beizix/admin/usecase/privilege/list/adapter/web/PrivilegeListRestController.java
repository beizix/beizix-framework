package org.beizix.admin.usecase.privilege.list.adapter.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.web.rest.RestResponse;
import org.beizix.admin.usecase.privilege.list.application.domain.PrivilegeElement;
import org.beizix.admin.usecase.privilege.list.application.port.in.PrivilegeListPortIn;
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
