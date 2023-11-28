package org.beizix.core.usecase.operationlog.save.application.port.out;

import org.beizix.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;

public interface OperationLogSavePortOut {
  void connect(OperationLogSaveCommand saveCommand);
}
