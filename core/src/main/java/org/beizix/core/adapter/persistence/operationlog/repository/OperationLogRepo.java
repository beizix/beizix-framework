package org.beizix.core.adapter.persistence.operationlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.adapter.persistence.operationlog.model.OperationLog;

public interface OperationLogRepo
    extends JpaRepository<OperationLog, Long>, JpaSpecificationExecutor<OperationLog> {}
