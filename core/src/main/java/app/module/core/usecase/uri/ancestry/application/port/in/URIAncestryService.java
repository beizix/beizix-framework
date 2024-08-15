package app.module.core.usecase.uri.ancestry.application.port.in;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.ancestry.application.domain.URIAncestry;
import app.module.core.usecase.uri.currentmatch.application.domain.URICurrentMatching;
import app.module.core.usecase.uri.view.application.port.in.URIViewPortIn;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.uri.view.application.domain.URIView;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URIAncestryService implements URIAncestryPortIn {

  private final app.module.core.usecase.uri.currentmatch.application.port.in.URICurrentMatchingPortIn URICurrentMatchingPortIn;
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
