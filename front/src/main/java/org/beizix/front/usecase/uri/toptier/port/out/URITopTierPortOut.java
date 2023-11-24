package org.beizix.front.usecase.uri.toptier.port.out;

import org.beizix.core.config.enums.AppType;
import org.beizix.front.usecase.uri.toptier.URITopTier;

public interface URITopTierPortOut {
  URITopTier connect(AppType appType);
}
