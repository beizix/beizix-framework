package org.beizix.admin.usecase.loggedinuser.view.application.port.in;

import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserIdView;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserView;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;

public interface LoggedInUserViewPortIn {
  LoggedInUserView connect(LoggedInUserIdView userCommand);
}
