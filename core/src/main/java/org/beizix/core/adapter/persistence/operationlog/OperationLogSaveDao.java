package org.beizix.core.adapter.persistence.operationlog;

import org.beizix.core.adapter.persistence.operationlog.repository.OperationLogRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.operationlog.OperationLogSavePortOut;
import org.beizix.core.adapter.persistence.operationlog.model.OperationLog;

@Repository
@RequiredArgsConstructor
class OperationLogSaveDao implements OperationLogSavePortOut {
  private final OperationLogRepo operationLogRepo;
  private final ModelMapper modelMapper;

  @Override
  public org.beizix.core.application.domain.operationlog.model.OperationLog connect(
      org.beizix.core.application.domain.operationlog.model.OperationLog operationLog) {
    OperationLog item =
        operationLogRepo.save(modelMapper.map(operationLog, OperationLog.class));
    return modelMapper.map(item, org.beizix.core.application.domain.operationlog.model.OperationLog.class);
  }
}
