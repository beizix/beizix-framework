package org.beizix.core.adapter.persistence.loggedinuser;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserRemovePortOut;
import org.beizix.core.adapter.persistence.loggedinuser.model.LoggedInUserIdEmbeddable;

@Repository
@RequiredArgsConstructor
public class LoggedInUserRemoveDao implements LoggedInUserRemovePortOut {
  private final LoggedInUserDao loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public void connect(LoggedInUserIdInput loggedInUserId) {
    loggedInUserRepo.deleteById(modelMapper.map(loggedInUserId, LoggedInUserIdEmbeddable.class));
  }
}
