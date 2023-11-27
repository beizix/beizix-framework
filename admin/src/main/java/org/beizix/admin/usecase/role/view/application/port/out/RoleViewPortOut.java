package org.beizix.admin.usecase.role.view.application.port.out;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleViewPortOut<E extends AuditOutput> {
  E connect(String role);
}
