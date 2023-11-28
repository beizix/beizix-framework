package org.beizix.core.usecase.operationlog.list.adapter.persistence;

import org.beizix.core.config.adapter.persistence.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperationLogListRepo extends JpaRepository<OperationLog, Long>, JpaSpecificationExecutor<OperationLog> {}
