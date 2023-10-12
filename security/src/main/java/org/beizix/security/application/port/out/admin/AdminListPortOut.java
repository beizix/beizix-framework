package org.beizix.security.application.port.out.admin;

import org.beizix.core.application.domain.common.model.ListPortOut;
import org.beizix.security.application.domain.admin.model.filter.AdminListStatus;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;

public interface AdminListPortOut extends ListPortOut<AdminListOutput, AdminListStatus> {}
