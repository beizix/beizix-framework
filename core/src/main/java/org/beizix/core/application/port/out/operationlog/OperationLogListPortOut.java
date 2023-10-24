package org.beizix.core.application.port.out.operationlog;

import org.beizix.core.application.domain.common.model.ListPortOut;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListStatus;
import org.beizix.core.application.domain.operationlog.model.list.OperationLogListOutput;

public interface OperationLogListPortOut
    extends ListPortOut<OperationLogListOutput, OperationLogListStatus> {}
