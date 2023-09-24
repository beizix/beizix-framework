package org.beizix.core.application.domain.loggedinuser;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.port.in.loggedinuser.LoggedInUserViewPortIn;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserViewPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserViewService implements LoggedInUserViewPortIn {
  private final LoggedInUserViewPortOut portOut;

  @Override
  public LoggedInUserInput connect(LoggedInUserIdInput loggedInUserId) {
    return portOut.connect(loggedInUserId);
  }
}
