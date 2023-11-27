package org.beizix.admin.usecase.role.list.application.port.in;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface RoleListPortIn<E extends AuditOutput> {
  List<E> connect();
}
