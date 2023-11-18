package org.beizix.core.application.domain.uri;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.port.in.uri.URIListPortIn;
import org.beizix.core.application.port.out.uri.URIListPortOut;

import java.util.List;

@Service
@RequiredArgsConstructor
class URIListService implements URIListPortIn {
  private final URIListPortOut uriListPortOut;

  @Cacheable("URIItemsByAppTypeCache")
  @Override
  public List<URIViewOutput> connect(AppType appType) {
    return uriListPortOut.connect(appType);
  }
}
