package org.beizix.admin.adapter.web.privilege;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.privilege.model.view.PrivilegeBindingVO;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.security.application.domain.privilege.model.view.PrivilegeOutput;
import org.beizix.security.application.port.in.privilege.PrivilegeViewPortIn;
import org.beizix.utility.common.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PrivilegeViewRestController {
  private final MessageUtil messageUtil;
  private final PrivilegeViewPortIn<PrivilegeOutput> privilegeViewPortIn;

  @GetMapping("/api/adminPrivilege/get/{id}")
  ResponseEntity<?> view(@PathVariable String id) {
    PrivilegeOutput item =
        Optional.ofNullable(privilegeViewPortIn.connect(id))
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", id, "ROLE ID")));

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(
                    new PrivilegeBindingVO(
                        item.getCreatedBy(),
                        item.getCreatedAt(),
                        item.getUpdatedBy(),
                        item.getUpdatedAt(),
                        item.getId(),
                        item.getDescription(),
                        item.getOrderNo()))
                .build());
  }
}
