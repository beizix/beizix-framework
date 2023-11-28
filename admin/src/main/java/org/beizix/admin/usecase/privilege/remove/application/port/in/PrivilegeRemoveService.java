package org.beizix.admin.usecase.privilege.remove.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.remove.application.port.out.PrivilegeRemovePortOut;
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
