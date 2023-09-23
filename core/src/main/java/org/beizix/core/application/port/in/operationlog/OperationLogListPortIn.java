package org.beizix.core.application.port.in.operationlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.core.application.domain.operationlog.model.OperationLog;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListInput;

public interface OperationLogListPortIn {
  Page<OperationLog> connect(
      Pageable pageable, OperationLogListInput operationLogListInput);
}
