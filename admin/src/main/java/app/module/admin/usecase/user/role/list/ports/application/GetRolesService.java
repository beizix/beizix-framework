package app.module.admin.usecase.user.role.list.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRoles;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRolesCmd;
import app.module.admin.usecase.user.role.list.ports.GetRolesPortIn;
import app.module.admin.usecase.user.role.list.ports.GetRolesPortOut;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetRolesService implements GetRolesPortIn {
  private final GetRolesPortOut portOut;

  @Override
  public List<GetRoles> operate(GetRolesCmd command) {
    return portOut.operate(command);
  }
}