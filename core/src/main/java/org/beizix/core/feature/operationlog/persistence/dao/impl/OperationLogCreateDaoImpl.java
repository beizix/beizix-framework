package org.beizix.core.feature.operationlog.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.operationlog.application.model.OperationLog;
import org.beizix.core.feature.operationlog.persistence.dao.OperationLogCreateDao;
import org.beizix.core.feature.operationlog.persistence.model.OperationLogEntity;

@Repository
@RequiredArgsConstructor
class OperationLogCreateDaoImpl implements OperationLogCreateDao {
  private final OperationLogRepo operationLogRepo;
  private final ModelMapper modelMapper;

  @Override
  public OperationLog operate(OperationLog operationLog) {
    OperationLogEntity item =
        operationLogRepo.save(modelMapper.map(operationLog, OperationLogEntity.class));
    return modelMapper.map(item, OperationLog.class);
  }
}
