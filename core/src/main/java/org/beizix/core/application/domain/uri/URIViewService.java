package org.beizix.core.application.domain.uri;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.port.in.uri.URIListPortIn;
import org.beizix.core.application.port.in.uri.URIViewPortIn;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class URIViewService implements URIViewPortIn {
  private final URIListPortIn uriListPortIn;

  @Override
  public Optional<URIOutput> connect(AppType appType, String id) {
    return uriListPortIn.connect(appType).stream()
        .filter(uri -> id.equals(uri.getId()))
        .findFirst();
  }
}
