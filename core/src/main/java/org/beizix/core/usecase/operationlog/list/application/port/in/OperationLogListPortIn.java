package org.beizix.core.usecase.operationlog.list.application.port.in;

import org.beizix.core.config.application.component.ListPortIn;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogPageableList;

public interface OperationLogListPortIn
    extends ListPortIn<OperationLogPageableList, OperationLogListFilterCommand> {}
