package org.beizix.admin.usecase.privilege.remove.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.remove.ports.PrivilegeRemovePortIn;
import org.beizix.admin.usecase.privilege.remove.ports.PrivilegeRemovePortOut;
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
