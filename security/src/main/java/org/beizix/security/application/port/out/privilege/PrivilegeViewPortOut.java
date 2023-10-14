package org.beizix.security.application.port.out.privilege;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeViewPortOut<E extends AuditOutput> {
  E connect(String id);
}
