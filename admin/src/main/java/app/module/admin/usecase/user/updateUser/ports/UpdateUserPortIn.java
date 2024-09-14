package app.module.admin.usecase.user.updateUser.ports;

import app.module.admin.usecase.user.updateUser.ports.application.domain.UpdateUserCmd;

public interface UpdateUserPortIn {
  void operate(UpdateUserCmd command);
}