package app.module.admin.usecase.privilege.view.ports.application;

import app.module.admin.usecase.privilege.view.ports.PrivilegeViewPortIn;
import app.module.admin.usecase.privilege.view.ports.PrivilegeViewPortOut;
import app.module.admin.usecase.privilege.view.ports.application.domain.PrivilegeView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeViewService implements PrivilegeViewPortIn<PrivilegeView> {
  private final PrivilegeViewPortOut<PrivilegeView> portOut;

  @Override
  public PrivilegeView connect(String id) {
    return portOut.connect(id);
  }
}
