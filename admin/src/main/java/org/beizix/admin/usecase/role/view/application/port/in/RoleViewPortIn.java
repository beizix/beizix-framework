package org.beizix.admin.usecase.role.view.application.port.in;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleViewPortIn<E extends AuditOutput> {
  E connect(String role);
}
