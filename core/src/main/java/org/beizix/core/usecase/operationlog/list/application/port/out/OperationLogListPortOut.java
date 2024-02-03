package org.beizix.core.usecase.operationlog.list.application.port.out;

import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogElement;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationLogListPortOut {
  Page<OperationLogElement> connect(Pageable pageable, OperationLogListFilterCommand condition);
}
