package org.beizix.core.application.port.in.loggedinuser;

import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;

public interface LoggedInUserRemovePortIn {
  void connect(LoggedInUserIdInput loggedInUserId);
}
