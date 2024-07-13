package org.beizix.admin.usecase.loggedinuser.remove.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.remove.ports.application.domain.LoggedInUserRemoveCmd;
import org.beizix.admin.usecase.loggedinuser.remove.ports.LoggedInUserRemovePortIn;
import org.beizix.admin.usecase.loggedinuser.remove.ports.LoggedInUserRemovePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserRemoveService implements LoggedInUserRemovePortIn {
  private final LoggedInUserRemovePortOut portOut;

  @Override
  public void connect(LoggedInUserRemoveCmd removeCommand) {
    portOut.connect(removeCommand);
  }
}
