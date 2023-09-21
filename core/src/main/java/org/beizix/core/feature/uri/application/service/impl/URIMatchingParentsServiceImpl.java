package org.beizix.core.feature.uri.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URIMatchingParentsService;
import org.beizix.core.feature.uri.application.service.URIMatchingService;
import org.beizix.core.feature.uri.application.service.URIViewService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class URIMatchingParentsServiceImpl implements URIMatchingParentsService {
  private final URIMatchingService uriMatchingService;
  private final URIViewService uriViewService;

  @Override
  public List<URI> operate(AppType appType, String uri) {
    List<URI> hierarchy = new ArrayList<>();
    URI currentURI = uriMatchingService.operate(appType, uri);
    hierarchy.add(currentURI);

    while (currentURI.getParentId() != null) {
      Optional<URI> optURI = uriViewService.operate(appType, currentURI.getParentId());
      if (optURI.isPresent()) {
        hierarchy.add(optURI.get());
        currentURI = optURI.get();
      }
    }

    Collections.reverse(hierarchy);

    return hierarchy;
  }
}
