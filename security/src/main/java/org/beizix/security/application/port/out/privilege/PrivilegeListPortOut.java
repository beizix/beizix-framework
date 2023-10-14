package org.beizix.security.application.port.out.privilege;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeListPortOut<E extends AuditOutput> {
  List<E> connect();
}
