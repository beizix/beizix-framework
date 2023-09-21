package org.beizix.core.feature.exboard.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.utility.common.MessageUtil;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.application.service.ExBoardViewService;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardViewDao;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class ExBoardViewServiceImpl implements ExBoardViewService {
  private final ExBoardViewDao exBoardViewDao;
  private final MessageUtil messageUtil;

  @Override
  public ExBoard operate(Long id) {
    return exBoardViewDao
        .operate(id)
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    messageUtil.getMessage("exception.noSuchElement", id, "ID")));
  }
}
