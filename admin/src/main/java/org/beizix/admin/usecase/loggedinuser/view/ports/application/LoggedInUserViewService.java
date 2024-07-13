package org.beizix.admin.usecase.loggedinuser.view.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;
import org.beizix.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortIn;
import org.beizix.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserViewService implements LoggedInUserViewPortIn {
  private final LoggedInUserViewPortOut portOut;

  @Override
  public LoggedInUserView connect(LoggedInUserIdCmd userCommand) {
    return portOut.connect(userCommand);
  }
}
