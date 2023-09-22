package org.beizix.admin.feature.role.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.security.application.port.in.role.RoleListPortIn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/adminRole")
class AdminUserRoleListController {
  private final RoleListPortIn roleListPortIn;
  private final ModelMapper modelMapper;

  @GetMapping("/get/recursiveItems")
  ResponseEntity<?> operate() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.<AdminUserRoleDto>builder()
                .items(
                    roleListPortIn.connect().stream()
                        .map(item -> modelMapper.map(item, AdminUserRoleDto.class))
                        .collect(Collectors.toList()))
                .build());
  }
}
