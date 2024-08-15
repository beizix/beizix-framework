package app.module.core.usecase.uri.list.application.port.in;

import java.util.List;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.list.application.domain.URIElement;
import app.module.core.usecase.uri.list.application.port.out.URIListPortOut;
import lombok.RequiredArgsConstructor;
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
