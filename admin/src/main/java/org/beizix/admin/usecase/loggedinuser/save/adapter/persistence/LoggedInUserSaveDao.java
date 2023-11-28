package org.beizix.admin.usecase.loggedinuser.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.save.application.port.out.LoggedInUserSavePortOut;
import org.beizix.admin.usecase.loggedinuser.save.application.domain.LoggedInUserSaveCommand;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserSaveDao implements LoggedInUserSavePortOut {
  private final LoggedInUserSaveRepo loggedInUserSaveRepo;
  private final ModelMapper modelMapper;

  @Override
  public void connect(LoggedInUserSaveCommand loggedInUser) {
    loggedInUserSaveRepo.save(modelMapper.map(loggedInUser, LoggedInUser.class));
  }
}
