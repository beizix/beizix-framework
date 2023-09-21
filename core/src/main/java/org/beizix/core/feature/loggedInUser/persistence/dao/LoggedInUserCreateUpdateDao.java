package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.beizix.core.feature.loggedInUser.application.model.LoggedInUser;

public interface LoggedInUserCreateUpdateDao {
  LoggedInUser operate(LoggedInUser loggedInUser);
}
