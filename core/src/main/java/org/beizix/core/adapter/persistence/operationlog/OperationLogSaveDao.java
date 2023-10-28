package org.beizix.core.adapter.persistence.operationlog;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.operationlog.model.OperationLog;
import org.beizix.core.adapter.persistence.operationlog.repository.OperationLogRepo;
import org.beizix.core.application.domain.operationlog.model.save.OperationLogInput;
import org.beizix.core.application.port.out.operationlog.OperationLogSavePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class OperationLogSaveDao implements OperationLogSavePortOut {
  private final OperationLogRepo operationLogRepo;

  @Override
  public void connect(OperationLogInput input) {
    operationLogRepo.save(
        new OperationLog(
            null,
            input.getAppType(),
            input.getOperationLogType(),
            input.getTargetId(),
            input.getIp(),
            input.getTaskDesc()));
  }
}
