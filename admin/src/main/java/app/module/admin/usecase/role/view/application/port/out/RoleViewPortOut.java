package app.module.admin.usecase.role.view.application.port.out;

import app.module.core.config.application.component.AuditOutput;

public interface RoleViewPortOut<E extends AuditOutput> {
  E connect(String role);
}
