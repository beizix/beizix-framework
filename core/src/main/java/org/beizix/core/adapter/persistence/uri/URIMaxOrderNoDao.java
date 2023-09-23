package org.beizix.core.adapter.persistence.uri;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.uri.repository.URIMaxOrderNoRepo;
import org.springframework.stereotype.Repository;
import org.beizix.core.application.port.out.uri.URIMaxOrderNoPortOut;

@Repository
@RequiredArgsConstructor
public class URIMaxOrderNoDao implements URIMaxOrderNoPortOut {
  private final URIMaxOrderNoRepo uriMaxOrderNoRepo;

  @Override
  public Integer connect(String parentId) {
    return uriMaxOrderNoRepo.getMaxOrderNo(parentId).orElse(-1);
  }
}
