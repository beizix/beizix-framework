package app.module.admin.usecase.user.updateUser.ports;

import app.module.admin.usecase.user.updateUser.ports.application.domain.UpdateUserCmd;

public interface UpdateUserPortOut {
  void operate(UpdateUserCmd command);
}