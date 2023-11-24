package org.beizix.admin.usecase.uri.toptier.application.port.out;

import org.beizix.admin.usecase.uri.toptier.domain.URITopTier;
import org.beizix.core.config.enums.AppType;

public interface URITopTierPortOut {
  URITopTier connect(AppType appType);
}
