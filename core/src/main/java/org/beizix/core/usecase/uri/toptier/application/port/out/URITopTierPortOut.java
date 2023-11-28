package org.beizix.core.usecase.uri.toptier.application.port.out;

import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.usecase.uri.toptier.application.domain.URITopTier;

public interface URITopTierPortOut {
  URITopTier connect(AppType appType);
}
