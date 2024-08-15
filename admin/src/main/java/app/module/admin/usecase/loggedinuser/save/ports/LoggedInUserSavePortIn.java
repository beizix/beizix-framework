package app.module.admin.usecase.loggedinuser.save.ports;

import app.module.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;

public interface LoggedInUserSavePortIn {
  void connect(LoggedInUserSaveCmd loggedInUserSaveCmd);
}
