package org.beizix.admin.usecase.loggedinuser.remove.ports;

import org.beizix.admin.usecase.loggedinuser.remove.ports.application.domain.LoggedInUserRemoveCmd;

public interface LoggedInUserRemovePortOut {
  void connect(LoggedInUserRemoveCmd removeCommand);
}
