package org.beizix.admin.usecase.admin.list.application.port.in;

import org.beizix.core.config.application.component.ListPortIn;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.AdminPageableList;

public interface AdminListPortIn extends ListPortIn<AdminPageableList, AdminListFilterCommand> {}
