package app.module.admin.usecase.privilege.remove.ports.application;

import app.module.admin.usecase.privilege.remove.ports.PrivilegeRemovePortIn;
import app.module.admin.usecase.privilege.remove.ports.PrivilegeRemovePortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeRemoveService implements PrivilegeRemovePortIn {
  private final PrivilegeRemovePortOut portOut;

  @Override
  public void connect(String id) {
    portOut.connect(id);
  }
}
