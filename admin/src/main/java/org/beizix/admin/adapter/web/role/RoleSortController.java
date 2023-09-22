package org.beizix.admin.adapter.web.role;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.role.model.sort.RoleSortReqVO;
import org.beizix.security.application.domain.role.model.sort.RoleSortInput;
import org.beizix.security.application.port.in.role.RoleSortPortIn;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RoleSortController {
  private final RoleSortPortIn roleSortPortIn;
  private final ModelMapper modelMapper;

  @PostMapping("/api/adminRole/switchOrderNo")
  ResponseEntity<?> switchOrderNo(@RequestBody RoleSortReqVO formDto) {
    roleSortPortIn.connect(
        formDto.getUpdateList().stream()
            .map(item -> modelMapper.map(item, RoleSortInput.class))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
