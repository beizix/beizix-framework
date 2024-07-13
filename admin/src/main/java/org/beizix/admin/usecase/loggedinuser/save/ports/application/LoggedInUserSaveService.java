package org.beizix.admin.usecase.loggedinuser.save.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.save.ports.LoggedInUserSavePortIn;
import org.beizix.admin.usecase.loggedinuser.save.ports.LoggedInUserSavePortOut;
import org.beizix.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserSaveService implements LoggedInUserSavePortIn {
  private final LoggedInUserSavePortOut portOut;

  @Override
  public void connect(LoggedInUserSaveCmd loggedInUserSaveCmd) {
    portOut.connect(loggedInUserSaveCmd);
  }
}
