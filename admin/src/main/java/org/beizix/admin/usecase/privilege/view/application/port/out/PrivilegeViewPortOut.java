package org.beizix.admin.usecase.privilege.view.application.port.out;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeViewPortOut<E extends AuditOutput> {
  E connect(String id);
}
