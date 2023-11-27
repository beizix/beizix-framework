package org.beizix.admin.usecase.loggedinuser.remove.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.remove.application.domain.LoggedInUserRemoveCommand;
import org.beizix.admin.usecase.loggedinuser.remove.application.port.out.LoggedInUserRemovePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserRemoveService implements LoggedInUserRemovePortIn {
  private final LoggedInUserRemovePortOut portOut;

  @Override
  public void connect(LoggedInUserRemoveCommand removeCommand) {
    portOut.connect(removeCommand);
  }
}
