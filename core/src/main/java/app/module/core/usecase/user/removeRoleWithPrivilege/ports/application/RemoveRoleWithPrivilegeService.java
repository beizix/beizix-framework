package app.module.core.usecase.user.removeRoleWithPrivilege.ports.application;

import app.module.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.user.removeRoleWithPrivilege.ports.RemoveRoleWithPrivilegePortIn;
import app.module.core.usecase.user.removeRoleWithPrivilege.ports.RemoveRoleWithPrivilegePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RemoveRoleWithPrivilegeService implements RemoveRoleWithPrivilegePortIn {
  private final RemoveRoleWithPrivilegePortOut portOut;

  @Override
  public void operate(RemoveRoleWithPrivilegeCmd command) {
    portOut.operate(command);
  }
}