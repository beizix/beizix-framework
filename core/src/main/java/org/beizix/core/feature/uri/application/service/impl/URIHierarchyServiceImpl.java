package org.beizix.core.feature.uri.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URIHierarchyService;
import org.beizix.core.feature.uri.application.service.URIListService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class URIHierarchyServiceImpl implements URIHierarchyService {
  private final URIListService uriListService;

  @Cacheable("URITopItemCache")
  @Override
  public URI operate(AppType appType) {
    Optional<URI> topURI =
        uriListService.operate(appType).stream()
            .filter(uri -> uri.getParentId() == null)
            .findFirst();
    topURI.ifPresent(uri -> scanningNodes(uri, 1));
    return topURI.orElse(URI.builder().build());
  }

  private void scanningNodes(URI node, int startDepth) {
    node.setDepth(startDepth++);

    if (!node.getNodes().isEmpty()) {

      // orderNo 오름차순 정렬
      node.getNodes()
          .sort(
              (menu1, menu2) -> {
                if (menu1.getOrderNo() > menu2.getOrderNo()) {
                  return 1;
                } else if (menu1.getOrderNo() < menu2.getOrderNo()) {
                  return -1;
                }
                return 0;
              });

      for (URI subURI : node.getNodes()) {
        scanningNodes(subURI, startDepth);
      }
    }
  }
}
