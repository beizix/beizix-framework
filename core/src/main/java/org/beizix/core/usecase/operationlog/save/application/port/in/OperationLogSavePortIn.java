package org.beizix.core.usecase.operationlog.save.application.port.in;

import org.beizix.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;

public interface OperationLogSavePortIn {
  void connect(OperationLogSaveCommand saveCommand);
}
