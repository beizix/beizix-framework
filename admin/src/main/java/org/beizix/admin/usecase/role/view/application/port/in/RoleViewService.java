package org.beizix.admin.usecase.role.view.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.role.view.application.domain.RoleView;
import org.beizix.admin.usecase.role.view.application.port.out.RoleViewPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleViewService implements RoleViewPortIn<RoleView> {
  private final RoleViewPortOut<RoleView> roleViewPortOut;

  @Override
  public RoleView connect(String role) {
    return roleViewPortOut.connect(role);
  }
}
