package org.beizix.core.application.domain.operationLog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.application.domain.operationLog.model.OperationLog;
import org.beizix.core.application.port.in.operationLog.OperationLogSavePortIn;
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
