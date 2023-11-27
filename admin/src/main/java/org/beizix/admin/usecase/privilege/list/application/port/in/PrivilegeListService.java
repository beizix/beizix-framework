package org.beizix.admin.usecase.privilege.list.application.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.list.application.domain.PrivilegeElement;
import org.beizix.admin.usecase.privilege.list.application.port.out.PrivilegeListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeListService implements PrivilegeListPortIn<PrivilegeElement> {
  private final PrivilegeListPortOut<PrivilegeElement> portOut;

  @Override
  public List<PrivilegeElement> connect() {
    return portOut.connect();
  }
}
