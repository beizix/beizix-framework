package app.module.core.usecase.operationlog.save.application.port.in;

import app.module.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;

public interface OperationLogSavePortIn {
  void connect(OperationLogSaveCommand saveCommand);
}
