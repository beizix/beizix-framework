package org.beizix.admin.feature.uri.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.service.URIHierarchyService;

@RestController
@RequiredArgsConstructor
class URIHierarchyController {
  private final URIHierarchyService uriHierarchyService;

  @GetMapping(path = "/api/uri/get/recursiveItems/{appType}")
  ResponseEntity<?> recursiveItems(@PathVariable AppType appType) {
    return ResponseEntity.status(HttpStatus.OK).body(uriHierarchyService.operate(appType));
  }
}
