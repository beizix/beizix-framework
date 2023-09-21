package org.beizix.core.feature.operationlog.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.core.feature.operationlog.application.model.OperationLog;
import org.beizix.core.feature.operationlog.application.model.OperationLogListCondition;

public interface OperationLogListService {
  Page<OperationLog> operate(
      Pageable pageable, OperationLogListCondition operationLogListCondition);
}
