package org.beizix.admin.usecase.loggedinuser.remove.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.remove.application.domain.LoggedInUserRemoveCommand;
import org.beizix.admin.usecase.loggedinuser.remove.application.port.out.LoggedInUserRemovePortOut;
import org.beizix.core.config.adapter.persistence.component.LoggedInUserEmbeddable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserRemoveDao implements LoggedInUserRemovePortOut {
  private final LoggedInUserRemoveRepo loggedInUserRepo;

  @Override
  public void connect(LoggedInUserRemoveCommand removeCommand) {
    loggedInUserRepo.deleteById(
        new LoggedInUserEmbeddable(removeCommand.getAppType(), removeCommand.getId()));
  }
}
