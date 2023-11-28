package org.beizix.admin.usecase.privilege.view.application.port.in;

import org.beizix.core.config.application.component.AuditOutput;

public interface PrivilegeViewPortIn<T extends AuditOutput> {
  T connect(String id);
}
