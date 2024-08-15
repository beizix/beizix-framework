package app.module.admin.usecase.uri.sort.application.port.in;

import java.util.List;

import app.module.admin.usecase.uri.sort.application.domain.URISortCommand;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uri.sort.application.port.out.URISortPortOut;
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
