package org.beizix.admin.usecase.admin.list.application.port.out;

import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.AdminPageableList;
import org.beizix.core.config.application.component.ListPortOut;

public interface AdminListPortOut extends ListPortOut<AdminPageableList, AdminListFilterCommand> {}
