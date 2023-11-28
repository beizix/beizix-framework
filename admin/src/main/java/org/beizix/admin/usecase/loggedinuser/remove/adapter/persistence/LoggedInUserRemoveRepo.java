package org.beizix.admin.usecase.loggedinuser.remove.adapter.persistence;

import org.beizix.core.config.adapter.persistence.component.LoggedInUserEmbeddable;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggedInUserRemoveRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
