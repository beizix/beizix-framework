package org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;
import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.RemoveRoleWithPrivilegePortIn;
import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.RemoveRoleWithPrivilegePortOut;
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