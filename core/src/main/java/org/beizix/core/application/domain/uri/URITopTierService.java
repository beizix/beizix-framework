package org.beizix.core.application.domain.uri;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.toptier.URITopTierOutput;
import org.beizix.core.application.port.in.uri.URITopTierPortIn;
import org.beizix.core.application.port.out.uri.URITopTierPortOut;
import org.beizix.core.config.enums.AppType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class URITopTierService implements URITopTierPortIn {
  private final URITopTierPortOut portOut;

  @Override
  public URITopTierOutput connect(AppType appType) {
    return portOut.connect(appType);
  }
}
