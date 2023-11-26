package org.beizix.core.usecase.uri.ancestry.application.port.in;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.uri.ancestry.application.domain.URIAncestry;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.usecase.uri.currentmatch.application.port.in.URICurrentMatchingPortIn;
import org.beizix.core.usecase.uri.currentmatch.application.domain.URICurrentMatching;
import org.beizix.core.usecase.uri.view.application.port.in.URIViewPortIn;
import org.beizix.core.usecase.uri.view.application.domain.URIView;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URIAncestryService implements URIAncestryPortIn {

  private final URICurrentMatchingPortIn URICurrentMatchingPortIn;
  private final URIViewPortIn uriViewPortIn;

  @Override
  public List<URIAncestry> connect(AppType appType, String uri) {
    List<URIAncestry> hierarchy = new ArrayList<>();
    URICurrentMatching currentMatchingURI = URICurrentMatchingPortIn.connect(appType, uri);
    URIAncestry mm =
        new URIAncestry(
            currentMatchingURI.getId(),
            currentMatchingURI.getParentId(),
            currentMatchingURI.getUri(),
            currentMatchingURI.getText());

    hierarchy.add(mm);

    while (mm.getParentId() != null) {
      Optional<URIView> parentURI = uriViewPortIn.connect(appType, mm.getParentId());
      if (parentURI.isPresent()) {
        mm =
            new URIAncestry(
                parentURI.get().getId(),
                parentURI.get().getParentId(),
                parentURI.get().getUri(),
                parentURI.get().getText());
        hierarchy.add(mm);
      }
    }

    Collections.reverse(hierarchy);

    return hierarchy;
  }
}
