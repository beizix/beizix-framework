package org.beizix.security.application.port.in.privilege;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeListPortIn<E extends AuditOutput> {
  List<E> connect();
}
