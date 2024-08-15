package app.module.core.usecase.user.removeRoleWithPrivilege.ports;

import app.module.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;

public interface RemoveRoleWithPrivilegePortIn {
  void operate(RemoveRoleWithPrivilegeCmd command);
}