package org.beizix.core.usecase.user.removeRoleWithPrivilege.ports;

import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;

public interface RemoveRoleWithPrivilegePortOut {
  void operate(RemoveRoleWithPrivilegeCmd command);
}