package org.beizix.core.application.domain.uri;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;
import org.beizix.core.application.port.in.uri.URIMatchingParentsPortIn;
import org.beizix.core.application.port.in.uri.URIMatchingPortIn;
import org.beizix.core.application.port.in.uri.URIViewPortIn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class URIMatchingParentsService implements URIMatchingParentsPortIn {
  private final URIMatchingPortIn uriMatchingPortIn;
  private final URIViewPortIn uriViewPortIn;

  @Override
  public List<URIInput> connect(AppType appType, String uri) {
    List<URIInput> hierarchy = new ArrayList<>();
    URIInput currentURI = uriMatchingPortIn.connect(appType, uri);
    hierarchy.add(currentURI);

    while (currentURI.getParentId() != null) {
      Optional<URIInput> optURI = uriViewPortIn.connect(appType, currentURI.getParentId());
      if (optURI.isPresent()) {
        hierarchy.add(optURI.get());
        currentURI = optURI.get();
      }
    }

    Collections.reverse(hierarchy);

    return hierarchy;
  }
}
