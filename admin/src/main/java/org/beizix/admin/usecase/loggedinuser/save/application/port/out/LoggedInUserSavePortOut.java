package org.beizix.admin.usecase.loggedinuser.save.application.port.out;

import org.beizix.admin.usecase.loggedinuser.save.application.domain.LoggedInUserSaveCommand;

public interface LoggedInUserSavePortOut {
  void connect(LoggedInUserSaveCommand loggedInUser);
}
