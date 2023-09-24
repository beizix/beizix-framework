package org.beizix.core.adapter.persistence.loggedinuser;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserSavePortOut;
import org.beizix.core.adapter.persistence.loggedinuser.model.LoggedInUser;

@Repository
@RequiredArgsConstructor
public class LoggedInUserSaveDao implements LoggedInUserSavePortOut {
  private final LoggedInUserDao loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public LoggedInUserInput connect(LoggedInUserInput loggedInUser) {
    return modelMapper.map(
        loggedInUserRepo.save(modelMapper.map(loggedInUser, LoggedInUser.class)),
        LoggedInUserInput.class);
  }
}
