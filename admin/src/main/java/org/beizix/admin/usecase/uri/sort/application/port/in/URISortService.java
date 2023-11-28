package org.beizix.admin.usecase.uri.sort.application.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uri.sort.application.port.out.URISortPortOut;
import org.beizix.admin.usecase.uri.sort.application.domain.URISortCommand;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URISortService implements URISortPortIn {
  private final URISortPortOut uriSortPortOut;

  @CacheEvict(
      value = {"URIItemsByAppTypeCache", "URITopTierCache"},
      allEntries = true)
  @Override
  public void connect(List<URISortCommand> uris) {
    uriSortPortOut.connect(uris);
  }
}
