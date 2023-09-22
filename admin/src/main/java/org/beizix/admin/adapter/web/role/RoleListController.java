package org.beizix.admin.adapter.web.role;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.role.model.list.RoleListReqVO;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/adminRole")
class RoleListController {
  private final RoleListPortIn roleListPortIn;
  private final ModelMapper modelMapper;

  @GetMapping("/get/recursiveItems")
  ResponseEntity<?> operate() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.<RoleListReqVO>builder()
                .items(
                    roleListPortIn.connect().stream()
                        .map(item -> modelMapper.map(item, RoleListReqVO.class))
                        .collect(Collectors.toList()))
                .build());
  }
}
