package org.beizix.core.application.port.out.loggedinuser;

import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;

public interface LoggedInUserRemovePortOut {
  void connect(LoggedInUserIdInput loggedInUserId);
}
