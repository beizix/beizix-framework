package org.beizix.core.application.port.in.loggedinuser;

import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;

public interface LoggedInUserViewPortIn {
  LoggedInUserInput connect(LoggedInUserIdInput loggedInUserId);
}
