package org.beizix.admin.usecase.loggedinuser.view.application.port.in;

import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserIdView;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserView;

public interface LoggedInUserViewPortIn {
  LoggedInUserView connect(LoggedInUserIdView userCommand);
}
