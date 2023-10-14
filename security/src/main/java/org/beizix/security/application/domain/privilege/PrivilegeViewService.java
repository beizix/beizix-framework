package org.beizix.security.application.domain.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.security.application.domain.privilege.model.view.PrivilegeOutput;
import org.beizix.security.application.port.in.privilege.PrivilegeViewPortIn;
import org.beizix.security.application.port.out.privilege.PrivilegeViewPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeViewService implements PrivilegeViewPortIn<PrivilegeOutput> {
  private final PrivilegeViewPortOut<PrivilegeOutput> portOut;

  @Override
  public PrivilegeOutput connect(String id) {
    return portOut.connect(id);
  }
}
