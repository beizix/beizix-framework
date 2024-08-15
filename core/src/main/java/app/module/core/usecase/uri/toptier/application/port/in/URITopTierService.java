package app.module.core.usecase.uri.toptier.application.port.in;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.toptier.application.domain.URITopTier;
import app.module.core.usecase.uri.toptier.application.port.out.URITopTierPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class URITopTierService implements URITopTierPortIn {

  private final URITopTierPortOut topTierPortOut;

  @Override
  @Cacheable("URITopTierCache")
  public URITopTier connect(AppType appType) {
    return topTierPortOut.connect(appType);
  }
}
