package org.beizix.core.feature.operationlog.persistence.dao;

import org.beizix.core.feature.operationlog.application.model.OperationLog;

public interface OperationLogCreateDao {
  OperationLog operate(OperationLog operationLog);
}
