package org.beizix.admin.usecase.privilege.list.ports;

import java.util.List;
import org.beizix.core.config.application.component.AuditOutput;

public interface PrivilegeListPortOut<E extends AuditOutput> {
  List<E> connect();
}
