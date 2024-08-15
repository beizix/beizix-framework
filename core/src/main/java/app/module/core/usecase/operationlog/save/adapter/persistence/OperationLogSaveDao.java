package app.module.core.usecase.operationlog.save.adapter.persistence;

import app.module.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import app.module.core.usecase.operationlog.save.application.port.out.OperationLogSavePortOut;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.persistence.entity.OperationLog;
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
