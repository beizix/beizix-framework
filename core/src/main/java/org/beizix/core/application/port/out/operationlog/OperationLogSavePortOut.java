package org.beizix.core.application.port.out.operationlog;

import org.beizix.core.application.domain.operationlog.model.save.OperationLogInput;

public interface OperationLogSavePortOut {
  void connect(OperationLogInput operationLog);
}
