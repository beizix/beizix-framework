package org.beizix.admin.usecase.loggedinuser.view.ports;

import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;

public interface LoggedInUserViewPortOut {
  LoggedInUserView connect(LoggedInUserIdCmd userCommand);
}
