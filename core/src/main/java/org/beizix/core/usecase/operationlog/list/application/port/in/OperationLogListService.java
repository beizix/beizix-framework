package org.beizix.core.usecase.operationlog.list.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.application.component.PageableInput;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogPageableList;
import org.beizix.core.usecase.operationlog.list.application.port.out.OperationLogListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationLogListService implements OperationLogListPortIn {
  private final OperationLogListPortOut portOut;

  @Override
  public OperationLogPageableList connect(
      PageableInput pageableInput, OperationLogListFilterCommand condition) {
    return portOut.connect(pageableInput, condition);
  }
}
