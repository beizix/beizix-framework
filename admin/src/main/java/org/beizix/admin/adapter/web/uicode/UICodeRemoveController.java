package org.beizix.admin.adapter.web.uicode;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.uicode.model.UICodeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.application.port.in.uicode.UICodeRemovePortIn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeRemoveController {
  private final UICodeRemovePortIn uiCodeRemovePortIn;

  @PostMapping("delete")
  ResponseEntity<?> process(UICodeDto formDto) {
    uiCodeRemovePortIn.operate(formDto.getId());
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
