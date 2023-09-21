package org.beizix.core.feature.operationlog.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.core.feature.operationlog.application.model.OperationLog;
import org.beizix.core.feature.operationlog.application.model.OperationLogListCondition;

public interface OperationLogListDao {
  Page<OperationLog> operate(
      Pageable pageable, OperationLogListCondition operationLogListCondition);
}
