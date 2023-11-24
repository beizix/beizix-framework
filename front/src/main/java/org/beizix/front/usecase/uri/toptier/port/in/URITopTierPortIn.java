package org.beizix.front.usecase.uri.toptier.port.in;

import org.beizix.core.config.enums.AppType;
import org.beizix.front.usecase.uri.toptier.URITopTier;

public interface URITopTierPortIn {
  URITopTier connect(AppType appType);
}
