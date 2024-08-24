package app.module.admin.usecase.user.role.list.ports;

import app.module.admin.usecase.user.role.list.ports.application.domain.GetRoles;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRolesCmd;
import java.util.List;

public interface GetRolesPortOut {
  List<GetRoles> operate(GetRolesCmd command);
}