package org.beizix.admin.usecase.loggedinuser.save.ports;

import org.beizix.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;

public interface LoggedInUserSavePortIn {
  void connect(LoggedInUserSaveCmd loggedInUserSaveCmd);
}
