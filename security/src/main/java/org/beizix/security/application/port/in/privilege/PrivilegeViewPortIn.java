package org.beizix.security.application.port.in.privilege;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeViewPortIn<T extends AuditOutput> {
  T connect(String id);
}
