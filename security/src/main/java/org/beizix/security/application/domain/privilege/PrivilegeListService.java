package org.beizix.security.application.domain.privilege;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.security.application.domain.privilege.model.list.PrivilegeOutput;
import org.beizix.security.application.port.in.privilege.PrivilegeListPortIn;
import org.beizix.security.application.port.out.privilege.PrivilegeListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeListService implements PrivilegeListPortIn<PrivilegeOutput> {
  private final PrivilegeListPortOut<PrivilegeOutput> portOut;

  @Override
  public List<PrivilegeOutput> connect() {
    return portOut.connect();
  }
}
