package org.beizix.admin.usecase.privilege.view.application.port.in;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeViewPortIn<T extends AuditOutput> {
  T connect(String id);
}
