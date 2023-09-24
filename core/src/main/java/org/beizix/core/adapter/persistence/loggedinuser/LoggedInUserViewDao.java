package org.beizix.core.adapter.persistence.loggedinuser;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserViewPortOut;
import org.beizix.core.adapter.persistence.loggedinuser.model.LoggedInUserIdEmbeddable;

@Repository
@RequiredArgsConstructor
public class LoggedInUserViewDao implements LoggedInUserViewPortOut {
  private final LoggedInUserDao loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public LoggedInUserInput connect(LoggedInUserIdInput loggedInUserId) {
    return loggedInUserRepo
        .findById(modelMapper.map(loggedInUserId, LoggedInUserIdEmbeddable.class))
        .map(loggedInUser -> modelMapper.map(loggedInUser, LoggedInUserInput.class))
        .orElse(null);
  }
}
