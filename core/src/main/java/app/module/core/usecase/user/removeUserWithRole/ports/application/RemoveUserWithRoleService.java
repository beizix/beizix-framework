package app.module.core.usecase.user.removeUserWithRole.ports.application;

import app.module.core.usecase.user.removeUserWithRole.ports.application.domain.RemoveUserWithRoleCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.user.removeUserWithRole.ports.RemoveUserWithRolePortIn;
import app.module.core.usecase.user.removeUserWithRole.ports.RemoveUserWithRolePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RemoveUserWithRoleService implements RemoveUserWithRolePortIn {
  private final RemoveUserWithRolePortOut portOut;

  @Override
  public void operate(RemoveUserWithRoleCmd command) {
    portOut.operate(command);
  }
}