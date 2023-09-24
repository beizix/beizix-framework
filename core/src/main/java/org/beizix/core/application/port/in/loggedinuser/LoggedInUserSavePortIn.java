package org.beizix.core.application.port.in.loggedinuser;

import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;

public interface LoggedInUserSavePortIn {
  LoggedInUserInput connect(LoggedInUserInput loggedInUserInput);
}
