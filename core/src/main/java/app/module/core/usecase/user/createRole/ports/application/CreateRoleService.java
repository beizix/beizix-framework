package app.module.core.usecase.user.createRole.ports.application;

import app.module.core.usecase.user.createRole.ports.application.domain.CreateRole;
import app.module.core.usecase.user.createRole.ports.application.domain.CreateRoleCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.user.createRole.ports.CreateRolePortIn;
import app.module.core.usecase.user.createRole.ports.CreateRolePortOut;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateRoleService implements CreateRolePortIn {
  private final CreateRolePortOut portOut;

  @Override
  public Optional<CreateRole> operate(CreateRoleCmd command) {
    return portOut.operate(command);
  }
}