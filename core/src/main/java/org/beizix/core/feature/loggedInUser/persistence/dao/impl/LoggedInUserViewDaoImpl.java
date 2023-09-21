package org.beizix.core.feature.loggedInUser.persistence.dao.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUser;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;
import org.beizix.core.feature.loggedInUser.persistence.dao.LoggedInUserViewDao;
import org.beizix.core.feature.loggedInUser.persistence.model.LoggedInUserIdEmbeddable;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LoggedInUserViewDaoImpl implements LoggedInUserViewDao {
  private final LoggedInUserRepo loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public LoggedInUser operate(LoggedInUserId loggedInUserId) {
    return loggedInUserRepo
        .findById(modelMapper.map(loggedInUserId, LoggedInUserIdEmbeddable.class))
        .map(loggedInUser -> modelMapper.map(loggedInUser, LoggedInUser.class))
        .orElse(null);
  }
}
