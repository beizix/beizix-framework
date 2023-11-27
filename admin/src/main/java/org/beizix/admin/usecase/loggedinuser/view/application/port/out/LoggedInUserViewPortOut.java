package org.beizix.admin.usecase.loggedinuser.view.application.port.out;

import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserIdView;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserView;

public interface LoggedInUserViewPortOut {
  LoggedInUserView connect(LoggedInUserIdView userCommand);
}
