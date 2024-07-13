package org.beizix.admin.usecase.loggedinuser.remove.ports;

import org.beizix.admin.usecase.loggedinuser.remove.ports.application.domain.LoggedInUserRemoveCmd;

public interface LoggedInUserRemovePortIn {
  void connect(LoggedInUserRemoveCmd loggedInUserId);
}
