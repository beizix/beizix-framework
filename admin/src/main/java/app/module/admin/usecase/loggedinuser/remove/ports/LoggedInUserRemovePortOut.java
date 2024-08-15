package app.module.admin.usecase.loggedinuser.remove.ports;

import app.module.admin.usecase.loggedinuser.remove.ports.application.domain.LoggedInUserRemoveCmd;

public interface LoggedInUserRemovePortOut {
  void connect(LoggedInUserRemoveCmd removeCommand);
}
