package org.beizix.admin.feature.uicode.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.feature.uicode.application.service.UICodeHierarchyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeHierarchyController {
  private final UICodeHierarchyService hierarchyService;

  @GetMapping("/get/hierarchy")
  ResponseEntity<?> process() {
    return ResponseEntity.status(HttpStatus.OK).body(hierarchyService.operate());
  }
}
