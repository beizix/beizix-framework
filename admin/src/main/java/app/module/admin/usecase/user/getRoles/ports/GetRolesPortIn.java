package app.module.admin.usecase.user.getRoles.ports;

import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRoles;
import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRolesCmd;
import java.util.List;

public interface GetRolesPortIn {
  List<GetRoles> operate(GetRolesCmd command);
}