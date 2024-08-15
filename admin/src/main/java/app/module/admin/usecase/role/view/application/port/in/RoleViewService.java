package app.module.admin.usecase.role.view.application.port.in;

import app.module.admin.usecase.role.view.application.domain.RoleView;
import app.module.admin.usecase.role.view.application.port.out.RoleViewPortOut;
import lombok.RequiredArgsConstructor;
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
