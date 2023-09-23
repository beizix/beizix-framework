package org.beizix.core.application.domain.operationLog;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.beizix.core.application.domain.operationLog.model.OperationLog;
import org.beizix.core.application.domain.operationLog.model.filter.OperationLogListInput;
import org.beizix.core.application.port.in.operationLog.OperationLogListPortIn;
import org.beizix.core.application.port.out.operationlog.OperationLogListPortOut;

@Service
@RequiredArgsConstructor
public class OperationLogListService implements OperationLogListPortIn {
  private final OperationLogListPortOut operationLogListPortOut;

  @Override
  public Page<OperationLog> connect(
      Pageable pageable, OperationLogListInput operationLogListInput) {
    return operationLogListPortOut.connect(pageable, operationLogListInput);
  }
}
