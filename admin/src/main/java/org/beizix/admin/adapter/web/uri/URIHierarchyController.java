package org.beizix.admin.adapter.web.uri;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.in.uri.URITopTierPortIn;
import org.beizix.core.config.enums.AppType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class URIHierarchyController {
  private final URITopTierPortIn uriTopTierPortIn;

  @GetMapping(path = "/api/uri/get/recursiveItems/{appType}")
  ResponseEntity<?> recursiveItems(@PathVariable AppType appType) {
    return ResponseEntity.status(HttpStatus.OK).body(uriTopTierPortIn.connect(appType));
  }
}
