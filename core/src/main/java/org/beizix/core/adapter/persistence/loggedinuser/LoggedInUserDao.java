package org.beizix.core.adapter.persistence.loggedinuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.beizix.core.adapter.persistence.loggedinuser.model.LoggedInUserIdEmbeddable;
import org.beizix.core.adapter.persistence.loggedinuser.model.LoggedInUser;

public interface LoggedInUserDao extends JpaRepository<LoggedInUser, LoggedInUserIdEmbeddable> {}
