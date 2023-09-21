package org.beizix.admin.feature.role.web;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.security.application.domain.role.model.view.RoleViewResp;
import org.beizix.security.application.port.in.role.RoleViewService;
import org.beizix.utility.common.MessageUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/adminRole")
class AdminUserRoleViewController {
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;
  private final RoleViewService roleViewService;

  @GetMapping("get/{role}")
  ResponseEntity<?> view(@PathVariable String role) {
    RoleViewResp item =
        Optional.ofNullable(roleViewService.operate(role))
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", role, "ROLE ID")));

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.builder().item(modelMapper.map(item, AdminUserRoleDto.class)).build());
  }
}
