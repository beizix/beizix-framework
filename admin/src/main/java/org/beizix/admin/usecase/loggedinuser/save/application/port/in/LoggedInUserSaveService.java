package org.beizix.admin.usecase.loggedinuser.save.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.save.application.port.out.LoggedInUserSavePortOut;
import org.beizix.admin.usecase.loggedinuser.save.application.domain.LoggedInUserSaveCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserSaveService implements LoggedInUserSavePortIn {
  private final LoggedInUserSavePortOut portOut;

  @Override
  public void connect(LoggedInUserSaveCommand loggedInUserSaveCommand) {
    portOut.connect(loggedInUserSaveCommand);
  }
}
