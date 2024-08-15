package app.module.admin.usecase.role.list.adapters.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import app.module.admin.usecase.role.list.adapters.web.model.PrivilegeBindingVO;
import app.module.admin.usecase.role.list.adapters.web.model.RoleBindingVO;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.admin.usecase.role.list.ports.application.domain.RoleElement;
import app.module.admin.usecase.role.list.ports.RoleListPortIn;
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
