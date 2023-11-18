package org.beizix.core.application.domain.uri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.beizix.core.application.port.in.uri.URIMatchingParentsPortIn;
import org.beizix.core.application.port.in.uri.URIMatchingPortIn;
import org.beizix.core.application.port.in.uri.URIViewPortIn;
import org.beizix.core.config.enums.AppType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URIMatchingParentsService implements URIMatchingParentsPortIn {
  private final URIMatchingPortIn uriMatchingPortIn;
  private final URIViewPortIn uriViewPortIn;

  @Override
  public List<URIViewOutput> connect(AppType appType, String uri) {
    List<URIViewOutput> hierarchy = new ArrayList<>();
    URIViewOutput currentURI = uriMatchingPortIn.connect(appType, uri);
    hierarchy.add(currentURI);

    while (currentURI.getParentId() != null) {
      Optional<URIViewOutput> parentURI = uriViewPortIn.connect(appType, currentURI.getParentId());
      if (parentURI.isPresent()) {
        hierarchy.add(parentURI.get());
        currentURI = parentURI.get();
      }
    }

    Collections.reverse(hierarchy);

    return hierarchy;
  }
}
