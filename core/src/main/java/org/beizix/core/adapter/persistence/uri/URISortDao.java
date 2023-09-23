package org.beizix.core.adapter.persistence.uri;

import java.util.List;

import javax.transaction.Transactional;

import org.beizix.core.adapter.persistence.uri.repository.URISortRepo;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.URISortInput;
import org.beizix.core.application.port.out.uri.URISortPortOut;

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
