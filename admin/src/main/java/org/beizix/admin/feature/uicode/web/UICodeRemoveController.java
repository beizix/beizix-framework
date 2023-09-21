package org.beizix.admin.feature.uicode.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.feature.uicode.application.service.UICodeRemoveService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeRemoveController {
  private final UICodeRemoveService uiCodeRemoveService;

  @PostMapping("delete")
  ResponseEntity<?> process(UICodeDto formDto) {
    uiCodeRemoveService.operate(formDto.getId());
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
