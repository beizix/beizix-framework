package org.beizix.core.usecase.uri.toptier.application.port.in;

import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.usecase.uri.toptier.application.domain.URITopTier;

public interface URITopTierPortIn {
  URITopTier connect(AppType appType);
}
