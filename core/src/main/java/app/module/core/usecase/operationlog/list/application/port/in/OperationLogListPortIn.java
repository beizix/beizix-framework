package app.module.core.usecase.operationlog.list.application.port.in;

import app.module.core.usecase.operationlog.list.application.domain.OperationLogElement;
import app.module.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationLogListPortIn {
  Page<OperationLogElement> connect(Pageable pageable, OperationLogListFilterCommand condition);
}
