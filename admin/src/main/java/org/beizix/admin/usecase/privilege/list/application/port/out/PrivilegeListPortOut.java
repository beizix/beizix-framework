package org.beizix.admin.usecase.privilege.list.application.port.out;

import java.util.List;
import org.beizix.core.config.application.component.AuditOutput;

public interface PrivilegeListPortOut<E extends AuditOutput> {
  List<E> connect();
}
