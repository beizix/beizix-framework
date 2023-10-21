package org.beizix.admin.adapter.web.role;

import java.util.List;
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
class RoleSortRestController {
  private final RoleSortPortIn roleSortPortIn;
  private final ModelMapper modelMapper;

  @PostMapping("/api/adminRole/switchOrderNo")
  ResponseEntity<?> switchOrderNo(@RequestBody List<RoleSortReqVO> sortVOs) {
    roleSortPortIn.connect(
        sortVOs.stream()
            .map(item -> new RoleSortInput(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
