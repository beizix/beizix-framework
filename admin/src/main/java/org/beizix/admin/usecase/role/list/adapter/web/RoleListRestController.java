package org.beizix.admin.usecase.role.list.adapter.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.config.adapter.web.rest.response.RestResponse;
import org.beizix.admin.usecase.role.list.application.domain.RoleElement;
import org.beizix.admin.usecase.role.list.application.port.in.RoleListPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RoleListRestController {
  private final RoleListPortIn<RoleElement> roleListPortIn;

  @GetMapping("/api/adminRole/get/recursiveItems")
  ResponseEntity<?> operate() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.<RoleBindingVO>builder()
                .items(
                    roleListPortIn.connect().stream()
                        .map(
                            item ->
                                new RoleBindingVO(
                                    item.getCreatedBy(),
                                    item.getCreatedAt(),
                                    item.getUpdatedBy(),
                                    item.getUpdatedAt(),
                                    item.getId(),
                                    item.getDescription(),
                                    item.getOrderNo(),
                                    CollectionUtils.emptyIfNull(item.getPrivileges()).stream()
                                        .map(
                                            p ->
                                                new PrivilegeBindingVO(
                                                    p.getId(), p.getDescription()))
                                        .collect(Collectors.toList())))
                        .collect(Collectors.toList()))
                .build());
  }
}
