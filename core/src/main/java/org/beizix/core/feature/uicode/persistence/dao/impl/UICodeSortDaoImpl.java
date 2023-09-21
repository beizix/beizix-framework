package org.beizix.core.feature.uicode.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uicode.persistence.dao.UICodeSortDao;
import org.beizix.core.feature.uicode.persistence.dao.impl.repository.UICodeSortRepo;

@Repository
@RequiredArgsConstructor
class UICodeSortDaoImpl implements UICodeSortDao {
  private final UICodeSortRepo uiCodeSortRepo;

  @Override
  public void operate(String id, Integer orderNo) {
    uiCodeSortRepo.operate(id, orderNo);
  }
}
