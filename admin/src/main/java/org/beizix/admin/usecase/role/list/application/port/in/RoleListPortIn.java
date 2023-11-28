package org.beizix.admin.usecase.role.list.application.port.in;

import java.util.List;
import org.beizix.core.config.application.component.AuditOutput;

public interface RoleListPortIn<E extends AuditOutput> {
  List<E> connect();
}
