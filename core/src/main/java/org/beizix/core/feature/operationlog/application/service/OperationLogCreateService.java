package org.beizix.core.feature.operationlog.application.service;

import org.beizix.core.feature.operationlog.application.model.OperationLog;

public interface OperationLogCreateService {
  OperationLog operate(OperationLog operationLog);
}
