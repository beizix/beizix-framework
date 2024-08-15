package app.module.core.usecase.user.removeUserWithRole.ports;

import app.module.core.usecase.user.removeUserWithRole.ports.application.domain.RemoveUserWithRoleCmd;

public interface RemoveUserWithRolePortOut {
  void operate(RemoveUserWithRoleCmd command);
}