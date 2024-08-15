package app.module.admin.usecase.loggedinuser.remove.ports.application;

import app.module.admin.usecase.loggedinuser.remove.ports.LoggedInUserRemovePortIn;
import app.module.admin.usecase.loggedinuser.remove.ports.LoggedInUserRemovePortOut;
import app.module.admin.usecase.loggedinuser.remove.ports.application.domain.LoggedInUserRemoveCmd;
import lombok.RequiredArgsConstructor;
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
