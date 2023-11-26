package org.beizix.core.usecase.uri.toptier.application.port.in;

import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.usecase.uri.toptier.domain.URITopTier;

public interface URITopTierPortIn {
  URITopTier connect(AppType appType);
}
