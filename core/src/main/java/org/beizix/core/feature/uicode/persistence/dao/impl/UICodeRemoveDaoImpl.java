package org.beizix.core.feature.uicode.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uicode.persistence.dao.UICodeRemoveDao;
import org.beizix.core.feature.uicode.persistence.dao.impl.repository.UICodeRemoveRepo;

@Repository
@RequiredArgsConstructor
class UICodeRemoveDaoImpl implements UICodeRemoveDao {
  private final UICodeRemoveRepo uiCodeRemoveRepo;

  @Override
  public void operate(String id) {
    uiCodeRemoveRepo.deleteById(id);
  }
}
