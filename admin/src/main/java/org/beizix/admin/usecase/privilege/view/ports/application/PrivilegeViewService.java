package org.beizix.admin.usecase.privilege.view.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.view.ports.application.domain.PrivilegeView;
import org.beizix.admin.usecase.privilege.view.ports.PrivilegeViewPortIn;
import org.beizix.admin.usecase.privilege.view.ports.PrivilegeViewPortOut;
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
