package org.beizix.admin.usecase.loggedinuser.remove.application.port.in;

import org.beizix.admin.usecase.loggedinuser.remove.application.domain.LoggedInUserRemoveCommand;

public interface LoggedInUserRemovePortIn {
  void connect(LoggedInUserRemoveCommand loggedInUserId);
}
