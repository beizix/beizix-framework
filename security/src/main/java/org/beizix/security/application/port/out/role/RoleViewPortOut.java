package org.beizix.security.application.port.out.role;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleViewPortOut<E extends AuditOutput> {
  E connect(String role);
}
