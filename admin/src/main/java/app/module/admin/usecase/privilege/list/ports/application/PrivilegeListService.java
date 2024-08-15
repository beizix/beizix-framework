package app.module.admin.usecase.privilege.list.ports.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.list.ports.application.domain.PrivilegeElement;
import app.module.admin.usecase.privilege.list.ports.PrivilegeListPortIn;
import app.module.admin.usecase.privilege.list.ports.PrivilegeListPortOut;
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
