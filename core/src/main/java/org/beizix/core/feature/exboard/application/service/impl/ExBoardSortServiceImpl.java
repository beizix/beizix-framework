package org.beizix.core.feature.exboard.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.application.service.ExBoardSortService;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardSortDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExBoardSortServiceImpl implements ExBoardSortService {
  private final ExBoardSortDao exBoardSortDao;

  @Override
  @Transactional
  public void operate(List<ExBoard> sortItems) {
    sortItems.forEach(item -> exBoardSortDao.operate(item.getId(), item.getOrderNo()));
  }
}
