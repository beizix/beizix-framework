package app.module.core.usecase.operationlog.save.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationLogSaveRepo extends JpaRepository<OperationLog, Long> {}
