package org.beizix.admin.usecase.privilege.sort.adapters.web;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.sort.adapters.web.model.PrivilegeSortVO;
import org.beizix.admin.usecase.privilege.sort.ports.application.domain.PrivilegeSortCmd;
import org.beizix.admin.usecase.privilege.sort.ports.PrivilegeSortPortIn;
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
  ResponseEntity<?> switchOrderNo(@RequestBody List<PrivilegeSortVO> sortVOs) {
    sortPortIn.connect(
        sortVOs.stream()
            .map(s -> new PrivilegeSortCmd(s.getId(), s.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
