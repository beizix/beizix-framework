package org.beizix.admin.adapter.web.privilege;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.privilege.model.sort.PrivilegeSortReqVO;
import org.beizix.security.application.domain.privilege.model.sort.PrivilegeSortInput;
import org.beizix.security.application.port.in.privilege.PrivilegeSortPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PrivilegeSortRestController {
  private final PrivilegeSortPortIn sortPortIn;

  @PostMapping("/api/adminPrivilege/switchOrderNo")
  ResponseEntity<?> switchOrderNo(@RequestBody List<PrivilegeSortReqVO> sortVOs) {
    sortPortIn.connect(
        sortVOs.stream()
            .map(s -> new PrivilegeSortInput(s.getId(), s.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
