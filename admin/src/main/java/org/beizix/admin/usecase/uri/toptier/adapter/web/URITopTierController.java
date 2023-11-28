package org.beizix.admin.usecase.uri.toptier.adapter.web;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.usecase.uri.toptier.application.port.in.URITopTierPortIn;
import org.beizix.core.usecase.uri.toptier.application.domain.URITopTier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class URITopTierController {

  private final URITopTierPortIn topTierPortIn;

  @GetMapping(path = "/api/uri/topTier/{appType}")
  ResponseEntity<?> recursiveItems(@PathVariable AppType appType) {
    URITopTier topTierOutput = topTierPortIn.connect(appType);
    return ResponseEntity.status(HttpStatus.OK).body(recursiveMapping(topTierOutput));
  }

  private URITreeVO recursiveMapping(URITopTier output) {
    return new URITreeVO(
        output.getId(),
        output.getText(),
        output.getUri(),
        output.getOrderNo(),
        CollectionUtils.emptyIfNull(output.getChildren()).stream()
            .map(this::recursiveMapping)
            .collect(Collectors.toList()));
  }
}
