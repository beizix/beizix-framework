package org.beizix.admin.usecase.privilege.list.ports;

import java.util.List;
import org.beizix.core.config.application.component.AuditOutput;

public interface PrivilegeListPortIn<E extends AuditOutput> {
  List<E> connect();
}
