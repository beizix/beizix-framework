package app.module.admin.usecase.loggedinuser.view.ports;

import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;

public interface LoggedInUserViewPortOut {
  LoggedInUserView connect(LoggedInUserIdCmd userCommand);
}
