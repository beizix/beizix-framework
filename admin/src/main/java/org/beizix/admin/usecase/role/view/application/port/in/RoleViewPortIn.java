package org.beizix.admin.usecase.role.view.application.port.in;

import org.beizix.core.config.application.component.AuditOutput;

public interface RoleViewPortIn<E extends AuditOutput> {
  E connect(String role);
}
