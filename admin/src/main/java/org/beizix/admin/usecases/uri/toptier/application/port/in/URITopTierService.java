package org.beizix.admin.usecases.uri.toptier.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uri.toptier.domain.URITopTier;
import org.beizix.admin.usecases.uri.toptier.application.port.out.URITopTierPortOut;
import org.beizix.core.config.enums.AppType;
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
