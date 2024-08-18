package app.module.admin.usecase.role.list.ports;

import java.util.List;
import app.module.core.config.application.component.AuditOutput;

public interface RoleListPortIn<E extends AuditOutput> {
  List<E> connect();
}