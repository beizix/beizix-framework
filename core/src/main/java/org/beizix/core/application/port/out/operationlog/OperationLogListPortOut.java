package org.beizix.core.application.port.out.operationlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.core.application.domain.operationLog.model.OperationLog;
import org.beizix.core.application.domain.operationLog.model.filter.OperationLogListInput;

public interface OperationLogListPortOut {
  Page<OperationLog> connect(
      Pageable pageable, OperationLogListInput operationLogListInput);
}
