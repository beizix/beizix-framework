package org.beizix.front.usecase.uri.toptier.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.enums.AppType;
import org.beizix.front.usecase.uri.toptier.URITopTier;
import org.beizix.front.usecase.uri.toptier.port.out.URITopTierPortOut;
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
