package org.beizix.core.application.port.out.loggedinuser;

import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;

public interface LoggedInUserSavePortOut {
  LoggedInUserInput connect(LoggedInUserInput loggedInUser);
}
