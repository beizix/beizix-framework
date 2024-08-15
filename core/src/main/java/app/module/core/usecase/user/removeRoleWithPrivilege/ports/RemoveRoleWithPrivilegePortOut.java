package app.module.core.usecase.user.removeRoleWithPrivilege.ports;

import app.module.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;

public interface RemoveRoleWithPrivilegePortOut {
  void operate(RemoveRoleWithPrivilegeCmd command);
}