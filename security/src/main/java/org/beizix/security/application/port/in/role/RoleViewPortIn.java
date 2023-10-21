package org.beizix.security.application.port.in.role;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleViewPortIn<E extends AuditOutput> {
  E connect(String role);
}
