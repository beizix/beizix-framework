package org.beizix.admin.feature.uicode.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.feature.uicode.application.service.UICodeSortService;
import org.beizix.core.feature.uicode.application.model.UICodeSort;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeSortController {
  private final UICodeSortService uiCodeSortService;

  @PostMapping("/sort")
  ResponseEntity<?> process(@RequestBody UICodeDto formDto) {
    uiCodeSortService.operate(
        formDto.getUpdateList().stream()
            .map(item -> new UICodeSort(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
