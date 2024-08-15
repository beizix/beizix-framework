package app.module.core.usecase.uri.toptier.application.port.out;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.toptier.application.domain.URITopTier;

public interface URITopTierPortOut {
  URITopTier connect(AppType appType);
}
