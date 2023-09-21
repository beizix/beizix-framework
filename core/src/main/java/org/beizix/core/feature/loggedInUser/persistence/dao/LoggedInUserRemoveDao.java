package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;

public interface LoggedInUserRemoveDao {
  void operate(LoggedInUserId loggedInUserId);
}
