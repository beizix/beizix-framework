package app.module.admin.usecase.role.sort.adapter.web;

import java.util.List;
import java.util.stream.Collectors;

import app.module.admin.usecase.role.sort.application.domain.RoleSortCommand;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.role.sort.application.port.in.RoleSortPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RoleSortRestController {
  private final RoleSortPortIn roleSortPortIn;

  @PostMapping("/api/adminRole/switchOrderNo")
  ResponseEntity<?> switchOrderNo(@RequestBody List<RoleSortVO> sortVOs) {
    roleSortPortIn.connect(
        sortVOs.stream()
            .map(item -> new RoleSortCommand(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
