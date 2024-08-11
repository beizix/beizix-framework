package org.beizix.core.usecase.user.removeUserWithRole.ports;

import org.beizix.core.usecase.user.removeUserWithRole.ports.application.domain.RemoveUserWithRoleCmd;

public interface RemoveUserWithRolePortIn {
  void operate(RemoveUserWithRoleCmd command);
}