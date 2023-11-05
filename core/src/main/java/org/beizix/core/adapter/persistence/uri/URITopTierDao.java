package org.beizix.core.adapter.persistence.uri;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.adapter.persistence.uri.model.URI;
import org.beizix.core.adapter.persistence.uri.repository.URIRepo;
import org.beizix.core.application.domain.uri.model.toptier.URIOutput;
import org.beizix.core.application.port.out.uri.URITopTierPortOut;
import org.beizix.core.config.enums.AppType;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URITopTierDao implements URITopTierPortOut {
  private final URIRepo uriRepo;

  @Override
  public URIOutput connect(AppType appType) {
    URI topTier =
        uriRepo
            .findByAppTypeAndParentIdIsNull(appType)
            .orElseThrow(() -> new RuntimeException("There is no top tier item of " + appType));

    return recursiveMapping(topTier, 0);
  }

  private URIOutput recursiveMapping(URI uri, Integer depth) {
    return new URIOutput(
        uri.getId(),
        uri.getAppType(),
        uri.getParentId(),
        uri.getOrderNo(),
        uri.getText(),
        uri.getUri(),
        uri.getShowOnNavi(),
        uri.getOgTitle(),
        uri.getOgDesc(),
        uri.getOgKeywords(),
        uri.getOgImage(),
        CollectionUtils.emptyIfNull(uri.getNodes()).stream()
            .map(node -> recursiveMapping(node, depth + 1))
            .collect(Collectors.toList()),
        uri.getRoles(),
        depth);
  }
}
