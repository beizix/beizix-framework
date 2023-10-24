package org.beizix.core.application.port.in.operationlog;

import org.beizix.core.application.domain.common.model.ListPortIn;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListStatus;
import org.beizix.core.application.domain.operationlog.model.list.OperationLogListOutput;

public interface OperationLogListPortIn
    extends ListPortIn<OperationLogListOutput, OperationLogListStatus> {}
