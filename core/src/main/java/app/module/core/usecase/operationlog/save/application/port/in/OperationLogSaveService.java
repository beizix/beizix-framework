package app.module.core.usecase.operationlog.save.application.port.in;

import app.module.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import app.module.core.usecase.operationlog.save.application.port.out.OperationLogSavePortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OperationLogSaveService implements OperationLogSavePortIn {
  private final OperationLogSavePortOut operationLogSavePortout;

  @Override
  public void connect(OperationLogSaveCommand saveCommand) {
    operationLogSavePortout.connect(saveCommand);
  }
}
