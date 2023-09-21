package org.beizix.core.feature.exboard.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardRemoveDao;

@Repository
@RequiredArgsConstructor
class ExBoardRemoveDaoImpl implements ExBoardRemoveDao {
  private final ExBoardRepo exBoardRepo;

  @Override
  public void operate(List<Long> checkedIds) {
    exBoardRepo.deleteAllById(checkedIds);
  }
}
