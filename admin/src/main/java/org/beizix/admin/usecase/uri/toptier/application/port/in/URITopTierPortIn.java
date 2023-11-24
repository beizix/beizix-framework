package org.beizix.admin.usecase.uri.toptier.application.port.in;

import org.beizix.admin.usecase.uri.toptier.domain.URITopTier;
import org.beizix.core.config.enums.AppType;

public interface URITopTierPortIn {
  URITopTier connect(AppType appType);
}
