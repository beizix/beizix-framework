package org.beizix.admin.usecase.role.list.application.port.out;

import java.util.List;
import org.beizix.core.config.application.component.AuditOutput;

public interface RoleListPortOut<E extends AuditOutput> {
  List<E> connect();
}
