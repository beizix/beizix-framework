package org.beizix.core.application.domain.loggedinuser;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;
import org.beizix.core.application.port.in.loggedinuser.LoggedInUserRemovePortIn;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserRemovePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserRemoveService implements LoggedInUserRemovePortIn {
  private final LoggedInUserRemovePortOut portOut;

  @Override
  public void connect(LoggedInUserIdInput loggedInUserId) {
    portOut.connect(loggedInUserId);
  }
}
