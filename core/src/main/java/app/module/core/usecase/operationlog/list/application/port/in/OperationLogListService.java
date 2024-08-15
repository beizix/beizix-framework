package app.module.core.usecase.operationlog.list.application.port.in;

import app.module.core.usecase.operationlog.list.application.domain.OperationLogElement;
import app.module.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import app.module.core.usecase.operationlog.list.application.port.out.OperationLogListPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationLogListService implements OperationLogListPortIn {
  private final OperationLogListPortOut portOut;

  @Override
  public Page<OperationLogElement> connect(
      Pageable pageable, OperationLogListFilterCommand condition) {
    return portOut.connect(pageable, condition);
  }
}
