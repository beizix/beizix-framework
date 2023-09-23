package org.beizix.core.adapter.persistence.uri;

import org.beizix.core.adapter.persistence.uri.repository.URIRepo;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uri.URIRemovePortOut;

@Repository
@RequiredArgsConstructor
class URIRemoveDao implements URIRemovePortOut {
  private final URIRepo uriRepo;

  @Override
  public void connect(String id) {
    uriRepo.deleteById(id);
  }
}
