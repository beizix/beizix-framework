package org.beizix.core.application.domain.operationlog;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListStatus;
import org.beizix.core.application.domain.operationlog.model.list.OperationLogListOutput;
import org.beizix.core.application.port.in.operationlog.OperationLogListPortIn;
import org.beizix.core.application.port.out.operationlog.OperationLogListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationLogListService implements OperationLogListPortIn {
  private final OperationLogListPortOut portOut;

  @Override
  public OperationLogListOutput connect(
      PageableInput pageableInput, OperationLogListStatus condition) {
    return portOut.connect(pageableInput, condition);
  }
}
