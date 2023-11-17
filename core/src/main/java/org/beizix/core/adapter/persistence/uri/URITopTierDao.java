package org.beizix.core.adapter.persistence.uri;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.adapter.persistence.uri.model.URI;
import org.beizix.core.adapter.persistence.uri.repository.URIRepo;
import org.beizix.core.application.domain.uri.model.toptier.URITopTierOutput;
import org.beizix.core.application.port.out.uri.URITopTierPortOut;
import org.beizix.core.config.enums.AppType;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URITopTierDao implements URITopTierPortOut {
  private final URIRepo uriRepo;

  @Override
  public URITopTierOutput connect(AppType appType) {
    URI topTier =
        uriRepo
            .findByAppTypeAndParentIdIsNull(appType)
            .orElseThrow(() -> new RuntimeException("There is no top tier item of " + appType));

    return recursiveMapping(topTier);
  }

  private URITopTierOutput recursiveMapping(URI uri) {
    return new URITopTierOutput(
        uri.getId(),
        uri.getAppType(),
        uri.getText(),
        uri.getUri(),
        uri.getShowOnNavi(),
        CollectionUtils.emptyIfNull(uri.getChildren()).stream()
            .map(this::recursiveMapping)
            .collect(Collectors.toList()));
  }
}
