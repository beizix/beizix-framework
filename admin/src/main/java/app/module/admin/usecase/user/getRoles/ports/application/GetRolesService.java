package app.module.admin.usecase.user.getRoles.ports.application;

import app.module.admin.usecase.user.getRoles.ports.GetRolesPortIn;
import app.module.admin.usecase.user.getRoles.ports.GetRolesPortOut;
import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRoles;
import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRolesCmd;
import lombok.RequiredArgsConstructor;

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