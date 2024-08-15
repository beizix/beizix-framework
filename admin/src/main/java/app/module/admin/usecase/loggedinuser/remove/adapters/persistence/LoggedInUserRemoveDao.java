package app.module.admin.usecase.loggedinuser.remove.adapters.persistence;

import app.module.admin.usecase.loggedinuser.remove.ports.LoggedInUserRemovePortOut;
import app.module.admin.usecase.loggedinuser.remove.ports.application.domain.LoggedInUserRemoveCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserRemoveDao implements LoggedInUserRemovePortOut {
  private final LoggedInUserRemoveRepo loggedInUserRepo;

  @Override
  public void connect(LoggedInUserRemoveCmd removeCommand) {
    loggedInUserRepo.deleteById(
        new LoggedInUserEmbeddable(removeCommand.getAppType(), removeCommand.getId()));
  }
}
