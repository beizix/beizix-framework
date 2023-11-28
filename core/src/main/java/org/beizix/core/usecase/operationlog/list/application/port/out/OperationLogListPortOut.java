package org.beizix.core.usecase.operationlog.list.application.port.out;

import org.beizix.core.config.application.component.ListPortOut;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogPageableList;

public interface OperationLogListPortOut
    extends ListPortOut<OperationLogPageableList, OperationLogListFilterCommand> {}
