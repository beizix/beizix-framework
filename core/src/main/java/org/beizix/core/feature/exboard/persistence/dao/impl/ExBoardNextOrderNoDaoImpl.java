package org.beizix.core.feature.exboard.persistence.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardNextOrderNoDao;

@Repository
@RequiredArgsConstructor
public class ExBoardNextOrderNoDaoImpl implements ExBoardNextOrderNoDao {
  private final ExBoardMaxOrderNoRepo exBoardMaxOrderNoRepo;

  @Override
  public Integer operate() {
    return exBoardMaxOrderNoRepo.operate().orElse(-1) + 1;
  }
}
