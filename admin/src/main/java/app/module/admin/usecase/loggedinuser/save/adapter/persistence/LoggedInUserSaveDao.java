package app.module.admin.usecase.loggedinuser.save.adapter.persistence;

import app.module.admin.usecase.loggedinuser.save.ports.LoggedInUserSavePortOut;
import app.module.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import app.module.core.config.adapter.persistence.entity.LoggedInUser;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserSaveDao implements LoggedInUserSavePortOut {
  private final LoggedInUserSaveRepo loggedInUserSaveRepo;

  @Override
  public void connect(LoggedInUserSaveCmd saveCommand) {
    loggedInUserSaveRepo.save(
        new LoggedInUser(
            new LoggedInUserEmbeddable(
                saveCommand.getLoggedInUserId().getAppType(),
                saveCommand.getLoggedInUserId().getId()),
            saveCommand.getClientIP(),
            saveCommand.getLastLoggedInAt()));
  }
}
