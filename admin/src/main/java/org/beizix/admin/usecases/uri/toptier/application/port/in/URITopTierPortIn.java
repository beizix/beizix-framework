package org.beizix.admin.usecases.uri.toptier.application.port.in;

import org.beizix.admin.usecases.uri.toptier.domain.URITopTier;
import org.beizix.core.config.enums.AppType;

public interface URITopTierPortIn {
  URITopTier connect(AppType appType);
}
