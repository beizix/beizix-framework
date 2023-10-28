package org.beizix.core.application.domain.uri;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.beizix.core.application.port.in.uri.URIHierarchyPortIn;
import org.beizix.core.application.port.in.uri.URIListPortIn;
import org.beizix.core.config.enums.AppType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URIHierarchyService implements URIHierarchyPortIn {
  private final URIListPortIn uriListPortIn;

  @Cacheable("URITopItemCache")
  @Override
  public URIOutput connect(AppType appType) {
    Optional<URIOutput> topURI =
        uriListPortIn.connect(appType).stream()
            .filter(uri -> uri.getParentId() == null)
            .findFirst();
    topURI.ifPresent(uri -> scanningNodes(uri, 1));
    return topURI.orElse(URIOutput.builder().build());
  }

  private void scanningNodes(URIOutput node, int startDepth) {
    node.setDepth(startDepth++);

    if (!node.getNodes().isEmpty()) {
      for (URIOutput subURI : node.getNodes()) {
        scanningNodes(subURI, startDepth);
      }
    }
  }
}
