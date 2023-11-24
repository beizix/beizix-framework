package org.beizix.core.application.domain.uri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.matchparent.URIMatchParentVO;
import org.beizix.core.application.port.in.uri.URIMatchingParentsPortIn;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.usecase.uri.currentmatch.application.port.in.URICurrentMatchingPortIn;
import org.beizix.core.usecase.uri.currentmatch.domain.URICurrentMatching;
import org.beizix.core.usecase.uri.view.application.port.in.URIViewPortIn;
import org.beizix.core.usecase.uri.view.domain.URIView;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URIMatchingParentsService implements URIMatchingParentsPortIn {

  private final URICurrentMatchingPortIn URICurrentMatchingPortIn;
  private final URIViewPortIn uriViewPortIn;

  @Override
  public List<URIMatchParentVO> connect(AppType appType, String uri) {
    List<URIMatchParentVO> hierarchy = new ArrayList<>();
    URICurrentMatching currentMatchingURI = URICurrentMatchingPortIn.connect(appType, uri);
    URIMatchParentVO mm =
        new URIMatchParentVO(
            currentMatchingURI.getId(),
            currentMatchingURI.getParentId(),
            currentMatchingURI.getUri(),
            currentMatchingURI.getText());

    hierarchy.add(mm);

    while (mm.getParentId() != null) {
      Optional<URIView> parentURI = uriViewPortIn.connect(appType, mm.getParentId());
      if (parentURI.isPresent()) {
        mm =
            new URIMatchParentVO(
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
