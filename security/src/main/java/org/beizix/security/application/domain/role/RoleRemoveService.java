package org.beizix.security.application.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.port.in.role.RoleRemovePortIn;
import org.beizix.security.application.port.out.role.RoleRemovePortOut;

@Service
@RequiredArgsConstructor
public class RoleRemoveService implements RoleRemovePortIn {
  private final RoleRemovePortOut roleRemovePortOut;

  @Override
  public void connect(String role) {
    roleRemovePortOut.connect(role);
  }
}
