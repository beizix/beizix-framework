package org.beizix.core.usecase.operationlog.save.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import org.beizix.core.usecase.operationlog.save.application.port.out.OperationLogSavePortOut;
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
