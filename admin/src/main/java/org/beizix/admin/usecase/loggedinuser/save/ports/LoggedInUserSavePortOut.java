package org.beizix.admin.usecase.loggedinuser.save.ports;

import org.beizix.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;

public interface LoggedInUserSavePortOut {
  void connect(LoggedInUserSaveCmd loggedInUser);
}
