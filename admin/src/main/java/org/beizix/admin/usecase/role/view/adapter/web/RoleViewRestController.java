package org.beizix.admin.usecase.role.view.adapter.web;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.web.rest.RestResponse;
import org.beizix.admin.usecase.role.view.application.domain.RoleView;
import org.beizix.admin.usecase.role.view.application.port.in.RoleViewPortIn;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RoleViewRestController {
  private final MessageUtil messageUtil;
  private final RoleViewPortIn<RoleView> roleViewPortIn;

  @GetMapping("/api/adminRole/get/{role}")
  ResponseEntity<?> view(@PathVariable String role) {
    RoleView item =
        Optional.ofNullable(roleViewPortIn.connect(role))
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", role, "ROLE ID")));

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(
                    new RoleBindingVO(
                        item.getCreatedBy(),
                        item.getCreatedAt(),
                        item.getUpdatedBy(),
                        item.getUpdatedAt(),
                        item.getId(),
                        item.getDescription(),
                        item.getOrderNo(),
                        item.getPrivilegeIds()))
                .build());
  }
}
