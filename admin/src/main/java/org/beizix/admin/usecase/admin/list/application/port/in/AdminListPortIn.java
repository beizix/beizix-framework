package org.beizix.admin.usecase.admin.list.application.port.in;

import org.beizix.core.application.domain.common.model.ListPortIn;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.AdminPageableList;

public interface AdminListPortIn extends ListPortIn<AdminPageableList, AdminListFilterCommand> {}
