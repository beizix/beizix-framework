package app.module.admin.usecase.uri.sort.adapter.persistence;

import java.util.List;
import javax.transaction.Transactional;

import app.module.admin.usecase.uri.sort.application.domain.URISortCommand;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uri.sort.application.port.out.URISortPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class URISortDao implements URISortPortOut {
  private final URISortRepo uriSortRepo;

  @Transactional
  @Override
  public void connect(List<URISortCommand> uris) {
    uris.forEach(uri -> uriSortRepo.operate(uri.getId(), uri.getOrderNo()));
  }
}
