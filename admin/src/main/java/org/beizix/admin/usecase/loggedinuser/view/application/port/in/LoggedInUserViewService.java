package org.beizix.admin.usecase.loggedinuser.view.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserIdView;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserView;
import org.beizix.admin.usecase.loggedinuser.view.application.port.out.LoggedInUserViewPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserViewService implements LoggedInUserViewPortIn {
  private final LoggedInUserViewPortOut portOut;

  @Override
  public LoggedInUserView connect(LoggedInUserIdView userCommand) {
    return portOut.connect(userCommand);
  }
}
