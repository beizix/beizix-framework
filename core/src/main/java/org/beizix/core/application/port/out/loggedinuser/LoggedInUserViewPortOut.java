package org.beizix.core.application.port.out.loggedinuser;

import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;

public interface LoggedInUserViewPortOut {
  LoggedInUserInput connect(LoggedInUserIdInput loggedInUserId);
}
