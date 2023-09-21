package org.beizix.core.feature.operationlog.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.operationlog.application.model.OperationLog;
import org.beizix.core.feature.operationlog.application.service.OperationLogCreateService;
import org.beizix.core.feature.operationlog.persistence.dao.OperationLogCreateDao;

@Service
@RequiredArgsConstructor
class OperationLogCreateServiceImpl implements OperationLogCreateService {
  private final OperationLogCreateDao operationLogCreateDao;

  @Override
  public OperationLog operate(OperationLog operationLog) {
    return operationLogCreateDao.operate(operationLog);
  }
}
