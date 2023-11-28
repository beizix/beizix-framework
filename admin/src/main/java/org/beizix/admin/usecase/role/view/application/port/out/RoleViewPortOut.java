package org.beizix.admin.usecase.role.view.application.port.out;

import org.beizix.core.config.application.component.AuditOutput;

public interface RoleViewPortOut<E extends AuditOutput> {
  E connect(String role);
}
