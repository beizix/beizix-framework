package org.beizix.core.application.domain.uri;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.application.domain.uri.model.URISortInput;
import org.beizix.core.application.port.in.uri.URISortPortIn;
import org.beizix.core.application.port.out.uri.URISortPortOut;

import java.util.List;

@Service
@RequiredArgsConstructor
class URISortService implements URISortPortIn {
  private final URISortPortOut uriSortPortOut;

  @CacheEvict(
      value = {"URITopItemCache", "URIItemsByAppTypeCache"},
      allEntries = true)
  @Override
  public void connect(List<URISortInput> uris) {
    uriSortPortOut.connect(uris);
  }
}
