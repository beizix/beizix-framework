package app.module.core.usecase.uri.toptier.application.port.in;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.toptier.application.domain.URITopTier;

public interface URITopTierPortIn {
  URITopTier connect(AppType appType);
}
