package org.beizix.security.application.domain.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.security.application.port.in.privilege.PrivilegeRemovePortIn;
import org.beizix.security.application.port.out.privilege.PrivilegeRemovePortOut;
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
