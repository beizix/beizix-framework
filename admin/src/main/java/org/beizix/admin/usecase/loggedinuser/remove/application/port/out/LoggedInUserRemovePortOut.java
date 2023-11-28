package org.beizix.admin.usecase.loggedinuser.remove.application.port.out;

import org.beizix.admin.usecase.loggedinuser.remove.application.domain.LoggedInUserRemoveCommand;

public interface LoggedInUserRemovePortOut {
  void connect(LoggedInUserRemoveCommand removeCommand);
}
