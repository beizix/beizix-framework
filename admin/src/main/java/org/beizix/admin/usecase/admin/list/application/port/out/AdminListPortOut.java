package org.beizix.admin.usecase.admin.list.application.port.out;

import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.AdminPageableList;
import org.beizix.core.application.domain.common.model.ListPortOut;

public interface AdminListPortOut extends ListPortOut<AdminPageableList, AdminListFilterCommand> {}
