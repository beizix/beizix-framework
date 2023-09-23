package org.beizix.core.application.port.out.operationlog;

import org.beizix.core.application.domain.operationLog.model.OperationLog;

public interface OperationLogSavePortOut {
  OperationLog connect(OperationLog operationLog);
}
