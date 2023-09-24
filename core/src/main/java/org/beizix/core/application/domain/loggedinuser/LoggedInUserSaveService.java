package org.beizix.core.application.domain.loggedinuser;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.port.in.loggedinuser.LoggedInUserSavePortIn;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserSavePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserSaveService implements LoggedInUserSavePortIn {
  private final LoggedInUserSavePortOut portOut;

  @Override
  public LoggedInUserInput connect(LoggedInUserInput loggedInUserInput) {
    return portOut.connect(loggedInUserInput);
  }
}
