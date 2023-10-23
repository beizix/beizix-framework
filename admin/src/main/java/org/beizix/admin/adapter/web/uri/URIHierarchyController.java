package org.beizix.admin.adapter.web.uri;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.port.in.uri.URIHierarchyPortIn;

@RestController
@RequiredArgsConstructor
class URIHierarchyController {
  private final URIHierarchyPortIn uriHierarchyPortIn;

  @GetMapping(path = "/api/uri/get/recursiveItems/{appType}")
  ResponseEntity<?> recursiveItems(@PathVariable AppType appType) {
    return ResponseEntity.status(HttpStatus.OK).body(uriHierarchyPortIn.connect(appType));
  }
}
