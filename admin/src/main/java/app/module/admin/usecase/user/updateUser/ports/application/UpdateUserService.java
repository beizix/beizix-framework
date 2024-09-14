package app.module.admin.usecase.user.updateUser.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.user.updateUser.ports.application.domain.UpdateUserCmd;
import app.module.admin.usecase.user.updateUser.ports.UpdateUserPortIn;
import app.module.admin.usecase.user.updateUser.ports.UpdateUserPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UpdateUserService implements UpdateUserPortIn {
  private final UpdateUserPortOut portOut;

  @Override
  public void operate(UpdateUserCmd command) {
    portOut.operate(command);
  }
}