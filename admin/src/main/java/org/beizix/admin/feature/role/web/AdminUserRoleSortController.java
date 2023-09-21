package org.beizix.admin.feature.role.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.security.application.domain.role.model.sort.RoleSortReq;
import org.beizix.security.application.port.in.role.RoleSortService;

@RestController
@RequiredArgsConstructor
class AdminUserRoleSortController {
  private final RoleSortService roleSortService;
  private final ModelMapper modelMapper;

  @PostMapping("/api/adminRole/switchOrderNo")
  ResponseEntity<?> switchOrderNo(@RequestBody AdminUserRoleDto formDto) {
    roleSortService.operate(
        formDto.getUpdateList().stream()
            .map(item -> modelMapper.map(item, RoleSortReq.class))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
