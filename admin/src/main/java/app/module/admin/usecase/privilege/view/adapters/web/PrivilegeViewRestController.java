package app.module.admin.usecase.privilege.view.adapters.web;

import java.util.NoSuchElementException;
import java.util.Optional;

import app.module.admin.usecase.privilege.view.adapters.web.model.PrivilegeViewVO;
import app.module.admin.usecase.privilege.view.ports.PrivilegeViewPortIn;
import app.module.admin.usecase.privilege.view.ports.application.domain.PrivilegeView;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PrivilegeViewRestController {
  private final MessageUtil messageUtil;
  private final PrivilegeViewPortIn<PrivilegeView> privilegeViewPortIn;

  @GetMapping("/api/adminPrivilege/get/{id}")
  ResponseEntity<?> view(@PathVariable String id) {
    PrivilegeView item =
        Optional.ofNullable(privilegeViewPortIn.connect(id))
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", id, "ROLE ID")));

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(
                    new PrivilegeViewVO(
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
