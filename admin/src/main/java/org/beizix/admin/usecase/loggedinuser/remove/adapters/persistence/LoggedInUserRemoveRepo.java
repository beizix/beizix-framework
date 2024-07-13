package org.beizix.admin.usecase.loggedinuser.remove.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggedInUserRemoveRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
