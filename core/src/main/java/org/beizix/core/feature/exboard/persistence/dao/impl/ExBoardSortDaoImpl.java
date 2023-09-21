package org.beizix.core.feature.exboard.persistence.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardSortDao;

@Repository
@RequiredArgsConstructor
public class ExBoardSortDaoImpl implements ExBoardSortDao {
  private final ExBoardSortRepo exBoardSortRepo;

  @Override
  public void operate(Long id, Integer orderNo) {
    exBoardSortRepo.operate(id, orderNo);
  }
}
