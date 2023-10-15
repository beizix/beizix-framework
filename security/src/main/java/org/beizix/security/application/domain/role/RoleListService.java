package org.beizix.security.application.domain.role;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.security.application.domain.role.model.list.RoleOutput;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.beizix.security.application.port.out.role.RoleListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleListService implements RoleListPortIn<RoleOutput> {
  private final RoleListPortOut<RoleOutput> portOut;

  @Override
  @Transactional
  public List<RoleOutput> connect() {
    return portOut.connect();
  }
}
