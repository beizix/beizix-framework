package app.module.admin.usecase.loggedinuser.save.ports;

import app.module.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;

public interface LoggedInUserSavePortOut {
  void connect(LoggedInUserSaveCmd loggedInUser);
}
