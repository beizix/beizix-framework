package org.beizix.admin.usecase.privilege.view.application.port.out;

import org.beizix.core.config.application.component.AuditOutput;

public interface PrivilegeViewPortOut<E extends AuditOutput> {
  E connect(String id);
}
