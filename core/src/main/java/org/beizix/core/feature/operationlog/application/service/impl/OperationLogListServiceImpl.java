package org.beizix.core.feature.operationlog.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.operationlog.application.model.OperationLog;
import org.beizix.core.feature.operationlog.application.model.OperationLogListCondition;
import org.beizix.core.feature.operationlog.application.service.OperationLogListService;
import org.beizix.core.feature.operationlog.persistence.dao.OperationLogListDao;

@Service
@RequiredArgsConstructor
public class OperationLogListServiceImpl implements OperationLogListService {
  private final OperationLogListDao operationLogListDao;

  @Override
  public Page<OperationLog> operate(
      Pageable pageable, OperationLogListCondition operationLogListCondition) {
    return operationLogListDao.operate(pageable, operationLogListCondition);
  }
}
