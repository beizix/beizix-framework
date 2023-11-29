package org.beizix.admin.usecase.loggedinuser.view.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserIdView;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserView;
import org.beizix.admin.usecase.loggedinuser.view.application.port.out.LoggedInUserViewPortOut;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserViewDao implements LoggedInUserViewPortOut {
  private final LoggedInUserViewRepo loggedInUserRepo;

  @Override
  public LoggedInUserView connect(LoggedInUserIdView userCommand) {
    return loggedInUserRepo
        .findById(new LoggedInUserEmbeddable(userCommand.getAppType(), userCommand.getId()))
        .map(
            loggedInUser ->
                new LoggedInUserView(
                    new LoggedInUserIdView(
                        loggedInUser.getLoggedInUserId().getAppType(),
                        loggedInUser.getLoggedInUserId().getId()),
                    loggedInUser.getClientIP(),
                    loggedInUser.getLastLoggedInAt()))
        .orElse(null);
  }
}
