package app.module.admin.usecase.loggedinuser.view.ports.application;

import app.module.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortIn;
import app.module.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortOut;
import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;
import lombok.RequiredArgsConstructor;
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
