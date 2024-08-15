package app.module.admin.usecase.privilege.list.ports;

import java.util.List;
import app.module.core.config.application.component.AuditOutput;

public interface PrivilegeListPortIn<E extends AuditOutput> {
  List<E> connect();
}
