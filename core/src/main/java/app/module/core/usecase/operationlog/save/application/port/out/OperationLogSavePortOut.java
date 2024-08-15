package app.module.core.usecase.operationlog.save.application.port.out;

import app.module.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;

public interface OperationLogSavePortOut {
  void connect(OperationLogSaveCommand saveCommand);
}
