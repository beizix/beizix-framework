package org.beizix.core.application.domain.uri;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;
import org.beizix.core.application.port.in.uri.URIHierarchyPortIn;
import org.beizix.core.application.port.in.uri.URIListPortIn;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class URIHierarchyService implements URIHierarchyPortIn {
  private final URIListPortIn uriListPortIn;

  @Cacheable("URITopItemCache")
  @Override
  public URIInput connect(AppType appType) {
    Optional<URIInput> topURI =
        uriListPortIn.connect(appType).stream()
            .filter(uri -> uri.getParentId() == null)
            .findFirst();
    topURI.ifPresent(uri -> scanningNodes(uri, 1));
    return topURI.orElse(URIInput.builder().build());
  }

  private void scanningNodes(URIInput node, int startDepth) {
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

      for (URIInput subURI : node.getNodes()) {
        scanningNodes(subURI, startDepth);
      }
    }
  }
}
