package app.module.core.usecase.uri.toptier.adapter.persistence;

import java.util.stream.Collectors;
import javax.transaction.Transactional;

import app.module.core.config.adapter.persistence.entity.URI;
import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.toptier.application.domain.URITopTier;
import app.module.core.usecase.uri.toptier.application.port.out.URITopTierPortOut;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URITopTierDao implements URITopTierPortOut {

  private final URITopTierRepo topTierRepo;

  @Override
  @Transactional
  public URITopTier connect(AppType appType) {
    URI topTier =
        topTierRepo
            .findByAppTypeAndParentIdIsNull(appType)
            .orElseThrow(() -> new RuntimeException("There is no top tier item of " + appType));

    return recursiveMapping(topTier);
  }

  private URITopTier recursiveMapping(URI uri) {
    return new URITopTier(
        uri.getId(),
        uri.getAppType(),
        uri.getText(),
        uri.getUri(),
        uri.getShowOnNavi(),
        uri.getOrderNo(),
        CollectionUtils.emptyIfNull(uri.getChildren()).stream()
            .map(this::recursiveMapping)
            .collect(Collectors.toList()));
  }
}
