package org.beizix.core.application.domain.operationlog;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.operationlog.model.save.OperationLogInput;
import org.beizix.core.application.port.in.operationlog.OperationLogSavePortIn;
import org.beizix.core.application.port.out.operationlog.OperationLogSavePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OperationLogSaveService implements OperationLogSavePortIn {
  private final OperationLogSavePortOut operationLogSavePortout;

  @Override
  public void connect(OperationLogInput operationLog) {
    operationLogSavePortout.connect(operationLog);
  }
}
