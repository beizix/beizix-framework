package org.beizix.core.application.domain.operationlog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.application.domain.operationlog.model.OperationLog;
import org.beizix.core.application.port.in.operationlog.OperationLogSavePortIn;
import org.beizix.core.application.port.out.operationlog.OperationLogSavePortOut;

@Service
@RequiredArgsConstructor
class OperationLogSaveService implements OperationLogSavePortIn {
  private final OperationLogSavePortOut operationLogSavePortout;

  @Override
  public OperationLog connect(OperationLog operationLog) {
    return operationLogSavePortout.connect(operationLog);
  }
}
