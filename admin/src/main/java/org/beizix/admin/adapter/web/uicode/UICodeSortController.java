package org.beizix.admin.adapter.web.uicode;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uicode.save.adapter.web.UICodeSaveVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.application.port.in.uicode.UICodeSortPortIn;
import org.beizix.core.application.domain.uicode.model.UICodeSortInput;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeSortController {
  private final UICodeSortPortIn uiCodeSortPortIn;

  @PostMapping("/sort")
  ResponseEntity<?> process(@RequestBody UICodeSaveVO formDto) {
    uiCodeSortPortIn.connect(
        formDto.getUpdateList().stream()
            .map(item -> new UICodeSortInput(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
