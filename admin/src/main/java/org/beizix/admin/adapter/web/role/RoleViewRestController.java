package org.beizix.admin.adapter.web.role;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.role.model.view.RoleBindingVO;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.security.application.domain.role.model.view.RoleViewOutput;
import org.beizix.security.application.port.in.role.RoleViewPortIn;
import org.beizix.utility.common.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RoleViewRestController {
  private final MessageUtil messageUtil;
  private final RoleViewPortIn<RoleViewOutput> roleViewPortIn;

  @GetMapping("/api/adminRole/get/{role}")
  ResponseEntity<?> view(@PathVariable String role) {
    RoleViewOutput item =
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
