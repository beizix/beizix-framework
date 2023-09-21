package org.beizix.core.feature.loggedInUser.persistence.dao.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;
import org.beizix.core.feature.loggedInUser.persistence.dao.LoggedInUserRemoveDao;
import org.beizix.core.feature.loggedInUser.persistence.model.LoggedInUserIdEmbeddable;

@Repository
@RequiredArgsConstructor
public class LoggedInUserRemoveDaoImpl implements LoggedInUserRemoveDao {
  private final LoggedInUserRepo loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public void operate(LoggedInUserId loggedInUserId) {
    loggedInUserRepo.deleteById(modelMapper.map(loggedInUserId, LoggedInUserIdEmbeddable.class));
  }
}
