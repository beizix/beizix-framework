package org.beizix.core.feature.exboard.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.exboard.application.service.ExBoardRemoveService;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardRemoveDao;

import java.util.List;

@Service
@RequiredArgsConstructor
class ExBoardRemoveServiceImpl implements ExBoardRemoveService {
  private final ExBoardRemoveDao exBoardRemoveDao;

  @Override
  public void operate(List<Long> checkedIds) {
    exBoardRemoveDao.operate(checkedIds);
  }
}
