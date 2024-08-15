package app.module.admin.usecase.role.view.application.port.in;

import app.module.core.config.application.component.AuditOutput;

public interface RoleViewPortIn<E extends AuditOutput> {
  E connect(String role);
}
