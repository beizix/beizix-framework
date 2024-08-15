package app.module.core.usecase.operationlog.list.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperationLogListRepo extends JpaRepository<OperationLog, Long>, JpaSpecificationExecutor<OperationLog> {}
