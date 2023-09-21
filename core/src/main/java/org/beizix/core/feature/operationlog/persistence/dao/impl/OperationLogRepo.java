package org.beizix.core.feature.operationlog.persistence.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.feature.operationlog.persistence.model.OperationLogEntity;

public interface OperationLogRepo
    extends JpaRepository<OperationLogEntity, Long>, JpaSpecificationExecutor<OperationLogEntity> {}
