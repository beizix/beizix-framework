package org.beizix.core.feature.loggedInUser.persistence.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.beizix.core.feature.loggedInUser.persistence.model.LoggedInUserIdEmbeddable;
import org.beizix.core.feature.loggedInUser.persistence.model.LoggedInUserEntity;

public interface LoggedInUserRepo extends JpaRepository<LoggedInUserEntity, LoggedInUserIdEmbeddable> {}
