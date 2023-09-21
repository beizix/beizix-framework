package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.beizix.core.feature.loggedInUser.application.model.LoggedInUser;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;

public interface LoggedInUserViewDao {
  LoggedInUser operate(LoggedInUserId loggedInUserId);
}
