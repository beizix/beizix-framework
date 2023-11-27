package org.beizix.admin.usecase.role.remove.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.role.remove.application.port.out.RoleRemovePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleRemoveService implements RoleRemovePortIn {
  private final RoleRemovePortOut roleRemovePortOut;

  @Override
  public void connect(String role) {
    roleRemovePortOut.connect(role);
  }
}
