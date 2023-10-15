package org.beizix.security.application.port.in.role;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleListPortIn<E extends AuditOutput> {
  List<E> connect();
}
