package org.beizix.security.application.port.in.admin;

import org.beizix.core.application.domain.common.model.ListPortIn;
import org.beizix.security.application.domain.admin.model.filter.AdminListStatus;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;

public interface AdminListPortIn extends ListPortIn<AdminListOutput, AdminListStatus> {}
