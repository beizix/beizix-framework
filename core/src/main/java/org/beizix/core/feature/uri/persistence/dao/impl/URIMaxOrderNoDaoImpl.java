package org.beizix.core.feature.uri.persistence.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.core.feature.uri.persistence.dao.URIMaxOrderNoDao;

@Repository
@RequiredArgsConstructor
public class URIMaxOrderNoDaoImpl implements URIMaxOrderNoDao {
  private final URIMaxOrderNoRepo uriMaxOrderNoRepo;

  @Override
  public Integer operate(String parentId) {
    return uriMaxOrderNoRepo.getMaxOrderNo(parentId).orElse(-1);
  }
}
