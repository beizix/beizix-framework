package org.beizix.core.application.port.in.operationLog;

import org.beizix.core.application.domain.operationLog.model.OperationLog;

public interface OperationLogSavePortIn {
  OperationLog connect(OperationLog operationLog);
}
