package org.beizix.admin.adapter.web.uicode;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.application.port.in.uicode.UICodeHierarchyPortIn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeHierarchyController {
  private final UICodeHierarchyPortIn hierarchyService;

  @GetMapping("/get/hierarchy")
  ResponseEntity<?> process() {
    return ResponseEntity.status(HttpStatus.OK).body(hierarchyService.operate());
  }
}
