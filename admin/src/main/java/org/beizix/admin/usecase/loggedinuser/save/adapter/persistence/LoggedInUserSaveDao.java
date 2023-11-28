package org.beizix.admin.usecase.loggedinuser.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.save.application.domain.LoggedInUserSaveCommand;
import org.beizix.admin.usecase.loggedinuser.save.application.port.out.LoggedInUserSavePortOut;
import org.beizix.core.config.adapter.persistence.component.LoggedInUserEmbeddable;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUser;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserSaveDao implements LoggedInUserSavePortOut {
  private final LoggedInUserSaveRepo loggedInUserSaveRepo;

  @Override
  public void connect(LoggedInUserSaveCommand saveCommand) {
    loggedInUserSaveRepo.save(
        new LoggedInUser(
            new LoggedInUserEmbeddable(
                saveCommand.getLoggedInUserId().getAppType(),
                saveCommand.getLoggedInUserId().getId()),
            saveCommand.getClientIP(),
            saveCommand.getLastLoggedInAt()));
  }
}
