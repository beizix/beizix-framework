package org.beizix.admin.usecase.privilege.list.application.port.in;

import java.util.List;
import org.beizix.core.config.application.component.AuditOutput;

public interface PrivilegeListPortIn<E extends AuditOutput> {
  List<E> connect();
}
