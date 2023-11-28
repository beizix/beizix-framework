package org.beizix.admin.usecase.loggedinuser.save.application.port.in;

import org.beizix.admin.usecase.loggedinuser.save.application.domain.LoggedInUserSaveCommand;

public interface LoggedInUserSavePortIn {
  void connect(LoggedInUserSaveCommand loggedInUserSaveCommand);
}
