package org.beizix.admin.usecase.privilege.list.application.port.out;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeListPortOut<E extends AuditOutput> {
  List<E> connect();
}
