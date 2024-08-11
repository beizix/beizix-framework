package org.beizix.core.usecase.user.removeUserWithRole.ports;

import org.beizix.core.usecase.user.removeUserWithRole.ports.application.domain.RemoveUserWithRoleCmd;

public interface RemoveUserWithRolePortOut {
  void operate(RemoveUserWithRoleCmd command);
}