package org.beizix.core.application.port.in.operationlog;

import org.beizix.core.application.domain.operationlog.model.OperationLog;

public interface OperationLogSavePortIn {
  OperationLog connect(OperationLog operationLog);
}
