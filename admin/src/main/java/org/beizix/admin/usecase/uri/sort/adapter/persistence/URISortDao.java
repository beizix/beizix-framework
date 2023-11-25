package org.beizix.admin.usecase.uri.sort.adapter.persistence;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uri.sort.application.port.out.URISortPortOut;
import org.beizix.core.application.domain.uri.model.URISortInput;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class URISortDao implements URISortPortOut {
  private final URISortRepo uriSortRepo;

  @Transactional
  @Override
  public void connect(List<URISortInput> uris) {
    uris.forEach(uri -> uriSortRepo.operate(uri.getId(), uri.getOrderNo()));
  }
}
