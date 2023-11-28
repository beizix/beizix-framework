package org.beizix.core.usecase.operationlog.save.adapter.persistence;

import org.beizix.core.config.adapter.persistence.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationLogSaveRepo extends JpaRepository<OperationLog, Long> {}
