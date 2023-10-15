package org.beizix.security.application.domain.role;

import lombok.RequiredArgsConstructor;
import org.beizix.security.application.domain.role.model.view.RoleViewOutput;
import org.beizix.security.application.port.in.role.RoleViewPortIn;
import org.beizix.security.application.port.out.role.RoleViewPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleViewService implements RoleViewPortIn<RoleViewOutput> {
  private final RoleViewPortOut<RoleViewOutput> roleViewPortOut;

  @Override
  public RoleViewOutput connect(String role) {
    return roleViewPortOut.connect(role);
  }
}
