package app.module.admin.usecase.role.list.ports.application;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.role.list.ports.application.domain.RoleElement;
import app.module.admin.usecase.role.list.ports.RoleListPortIn;
import app.module.admin.usecase.role.list.ports.RoleListPortOut;
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
