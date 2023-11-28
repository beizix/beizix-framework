package org.beizix.core.usecase.operationlog.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.OperationLog;
import org.beizix.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import org.beizix.core.usecase.operationlog.save.application.port.out.OperationLogSavePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class OperationLogSaveDao implements OperationLogSavePortOut {
  private final OperationLogSaveRepo operationLogSaveRepo;

  @Override
  public void connect(OperationLogSaveCommand saveCommand) {
    operationLogSaveRepo.save(
        new OperationLog(
            null,
            saveCommand.getAppType(),
            saveCommand.getOperationLogType(),
            saveCommand.getTargetId(),
            saveCommand.getIp(),
            saveCommand.getTaskDesc()));
  }
}
