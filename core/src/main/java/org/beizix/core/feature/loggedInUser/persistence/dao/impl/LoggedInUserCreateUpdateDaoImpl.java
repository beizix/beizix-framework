package org.beizix.core.feature.loggedInUser.persistence.dao.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUser;
import org.beizix.core.feature.loggedInUser.persistence.dao.LoggedInUserCreateUpdateDao;
import org.beizix.core.feature.loggedInUser.persistence.model.LoggedInUserEntity;

@Repository
@RequiredArgsConstructor
public class LoggedInUserCreateUpdateDaoImpl implements LoggedInUserCreateUpdateDao {
  private final LoggedInUserRepo loggedInUserRepo;
  private final ModelMapper modelMapper;

  @Override
  public LoggedInUser operate(LoggedInUser loggedInUser) {
    return modelMapper.map(
        loggedInUserRepo.save(modelMapper.map(loggedInUser, LoggedInUserEntity.class)),
        LoggedInUser.class);
  }
}
