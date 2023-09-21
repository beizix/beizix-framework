package org.beizix.core.feature.uri.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URIListService;
import org.beizix.core.feature.uri.application.service.URIViewService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class URIViewServiceImpl implements URIViewService {
  private final URIListService uriListService;

  @Override
  public Optional<URI> operate(AppType appType, String id) {
    return uriListService.operate(appType).stream()
        .filter(uri -> id.equals(uri.getId()))
        .findFirst();
  }
}
