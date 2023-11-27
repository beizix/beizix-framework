package org.beizix.admin.usecase.loggedinuser.view.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserIdView;
import org.beizix.admin.usecase.loggedinuser.view.application.domain.LoggedInUserView;
import org.beizix.admin.usecase.loggedinuser.view.application.port.out.LoggedInUserViewPortOut;
import org.beizix.core.configuration.adapter.persistence.component.LoggedInUserEmbeddable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserViewDao implements LoggedInUserViewPortOut {
  private final LoggedInUserViewRepo loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public LoggedInUserView connect(LoggedInUserIdView userCommand) {
    return loggedInUserRepo
        .findById(new LoggedInUserEmbeddable(userCommand.getAppType(), userCommand.getId()))
        .map(loggedInUser -> modelMapper.map(loggedInUser, LoggedInUserView.class))
        .orElse(null);
  }
}
