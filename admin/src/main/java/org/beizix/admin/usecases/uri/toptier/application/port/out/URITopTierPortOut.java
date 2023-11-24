package org.beizix.admin.usecases.uri.toptier.application.port.out;

import org.beizix.admin.usecases.uri.toptier.domain.URITopTier;
import org.beizix.core.config.enums.AppType;

public interface URITopTierPortOut {
  URITopTier connect(AppType appType);
}
