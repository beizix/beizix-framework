package org.beizix.core.application.port.out.operationlog;

import org.beizix.core.application.domain.operationlog.model.OperationLog;

public interface OperationLogSavePortOut {
  OperationLog connect(OperationLog operationLog);
}
