package org.beizix.core.feature.uri.persistence.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uri.application.model.URISort;
import org.beizix.core.feature.uri.persistence.dao.URISortDao;

@Repository
@RequiredArgsConstructor
class URISortDaoImpl implements URISortDao {
  private final URISortRepo uriSortRepo;

  @Transactional
  @Override
  public void operate(List<URISort> uris) {
    uris.forEach(uri -> uriSortRepo.operate(uri.getId(), uri.getOrderNo()));
  }
}
