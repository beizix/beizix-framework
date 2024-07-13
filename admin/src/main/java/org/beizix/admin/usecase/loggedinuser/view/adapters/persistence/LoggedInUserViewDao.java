package org.beizix.admin.usecase.loggedinuser.view.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;
import org.beizix.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortOut;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
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
