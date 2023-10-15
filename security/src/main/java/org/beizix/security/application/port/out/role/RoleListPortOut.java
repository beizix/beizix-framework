package org.beizix.security.application.port.out.role;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleListPortOut<E extends AuditOutput> {
  List<E> connect();
}
