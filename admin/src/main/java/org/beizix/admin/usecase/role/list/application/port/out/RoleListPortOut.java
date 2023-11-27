package org.beizix.admin.usecase.role.list.application.port.out;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleListPortOut<E extends AuditOutput> {
  List<E> connect();
}
