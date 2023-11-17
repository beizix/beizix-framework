package org.beizix.admin.adapter.web.uri;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.adapter.web.uri.model.tree.URITreeVO;
import org.beizix.core.application.domain.uri.model.toptier.URITopTierOutput;
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
  private final URITopTierPortIn topTierPortIn;

  @GetMapping(path = "/api/uri/get/recursiveItems/{appType}")
  ResponseEntity<?> recursiveItems(@PathVariable AppType appType) {
    URITopTierOutput topTierOutput = topTierPortIn.connect(appType);
    return ResponseEntity.status(HttpStatus.OK).body(recursiveMapping(topTierOutput));
  }

  private URITreeVO recursiveMapping(URITopTierOutput output) {
    return new URITreeVO(
        output.getId(),
        output.getText(),
        output.getUri(),
        CollectionUtils.emptyIfNull(output.getChildren()).stream()
            .map(this::recursiveMapping)
            .collect(Collectors.toList()));
  }
}
