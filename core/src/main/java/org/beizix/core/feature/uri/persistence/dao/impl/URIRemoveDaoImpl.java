package org.beizix.core.feature.uri.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uri.persistence.dao.URIRemoveDao;

@Repository
@RequiredArgsConstructor
class URIRemoveDaoImpl implements URIRemoveDao {
  private final URIRepo uriRepo;

  @Override
  public void operate(String id) {
    uriRepo.deleteById(id);
  }
}
