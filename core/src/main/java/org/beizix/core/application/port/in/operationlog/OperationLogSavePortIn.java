package org.beizix.core.application.port.in.operationlog;

import org.beizix.core.application.domain.operationlog.model.save.OperationLogInput;

public interface OperationLogSavePortIn {
  void connect(OperationLogInput operationLogInput);
}
