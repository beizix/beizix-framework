package org.beizix.admin.usecases.uicode.sort.adapter.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uicode.sort.application.port.in.UICodeSortCommand;
import org.beizix.admin.usecases.uicode.sort.application.port.in.UICodeSortPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UICodeSortController {
  private final UICodeSortPortIn uiCodeSortPortIn;

  @PostMapping("/api/uicode/sort")
  ResponseEntity<?> process(@RequestBody UICodeSortVO sortVO) {
    uiCodeSortPortIn.connect(
        sortVO.getUpdateList().stream()
            .map(sortCompVO -> new UICodeSortCommand(sortCompVO.getId(), sortCompVO.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
