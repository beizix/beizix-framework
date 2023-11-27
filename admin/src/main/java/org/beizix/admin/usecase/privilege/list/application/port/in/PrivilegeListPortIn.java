package org.beizix.admin.usecase.privilege.list.application.port.in;

import java.util.List;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface PrivilegeListPortIn<E extends AuditOutput> {
  List<E> connect();
}
