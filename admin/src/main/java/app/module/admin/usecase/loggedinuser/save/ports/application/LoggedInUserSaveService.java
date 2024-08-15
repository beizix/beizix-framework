package app.module.admin.usecase.loggedinuser.save.ports.application;

import app.module.admin.usecase.loggedinuser.save.ports.LoggedInUserSavePortIn;
import app.module.admin.usecase.loggedinuser.save.ports.LoggedInUserSavePortOut;
import app.module.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;
import lombok.RequiredArgsConstructor;
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
