package org.beizix.core.feature.exboard.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.application.model.ExBoardListCondition;
import org.beizix.core.feature.exboard.application.service.ExBoardListService;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardListDao;

@Service
@RequiredArgsConstructor
class ExBoardListServiceImpl implements ExBoardListService {
  private final ExBoardListDao exBoardListDao;

  @Override
  public Page<ExBoard> operate(Pageable pageable, ExBoardListCondition exBoardListCondition) {
    return exBoardListDao.operate(pageable, exBoardListCondition);
  }
}
