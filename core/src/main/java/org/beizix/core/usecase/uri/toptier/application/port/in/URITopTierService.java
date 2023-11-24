package org.beizix.core.usecase.uri.toptier.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.usecase.uri.toptier.application.port.out.URITopTierPortOut;
import org.beizix.core.usecase.uri.toptier.domain.URITopTier;
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
