package app.module.admin.usecase.loggedinuser.view.adapters.persistence;

import app.module.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortOut;
import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserViewDao implements LoggedInUserViewPortOut {
  private final LoggedInUserViewRepo loggedInUserRepo;

  @Override
  public LoggedInUserView connect(LoggedInUserIdCmd userCommand) {
    return loggedInUserRepo
        .findById(new LoggedInUserEmbeddable(userCommand.getAppType(), userCommand.getId()))
        .map(
            loggedInUser ->
                new LoggedInUserView(
                    new LoggedInUserIdCmd(
                        loggedInUser.getLoggedInUserId().getAppType(),
                        loggedInUser.getLoggedInUserId().getId()),
                    loggedInUser.getClientIP(),
                    loggedInUser.getLastLoggedInAt()))
        .orElse(null);
  }
}
