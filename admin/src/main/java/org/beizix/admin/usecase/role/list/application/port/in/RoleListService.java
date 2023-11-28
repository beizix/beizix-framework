package org.beizix.admin.usecase.role.list.application.port.in;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.role.list.application.domain.RoleElement;
import org.beizix.admin.usecase.role.list.application.port.out.RoleListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleListService implements RoleListPortIn<RoleElement> {
  private final RoleListPortOut<RoleElement> portOut;

  @Override
  @Transactional
  public List<RoleElement> connect() {
    return portOut.connect();
  }
}
