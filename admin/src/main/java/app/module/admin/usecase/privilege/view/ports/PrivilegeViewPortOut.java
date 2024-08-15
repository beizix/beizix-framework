package app.module.admin.usecase.privilege.view.ports;

import app.module.core.config.application.component.AuditOutput;

public interface PrivilegeViewPortOut<E extends AuditOutput> {
  E connect(String id);
}
