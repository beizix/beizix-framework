package org.beizix.core.usecase.uri.list.application.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.uri.list.application.port.out.URIListPortOut;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.usecase.uri.list.domain.URIElement;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URIListService implements URIListPortIn {

  private final URIListPortOut uriListPortOut;

  @Cacheable("URIItemsByAppTypeCache")
  @Override
  public List<URIElement> connect(AppType appType) {
    return uriListPortOut.connect(appType);
  }
}
