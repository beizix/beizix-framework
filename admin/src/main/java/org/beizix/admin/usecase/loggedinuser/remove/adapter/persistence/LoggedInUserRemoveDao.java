package org.beizix.admin.usecase.loggedinuser.remove.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.loggedinuser.remove.application.domain.LoggedInUserRemoveCommand;
import org.beizix.admin.usecase.loggedinuser.remove.application.port.out.LoggedInUserRemovePortOut;
import org.beizix.core.configuration.adapter.persistence.component.LoggedInUserEmbeddable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoggedInUserRemoveDao implements LoggedInUserRemovePortOut {
  private final LoggedInUserRemoveRepo loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public void connect(LoggedInUserRemoveCommand removeCommand) {
    loggedInUserRepo.deleteById(modelMapper.map(removeCommand, LoggedInUserEmbeddable.class));
  }
}
