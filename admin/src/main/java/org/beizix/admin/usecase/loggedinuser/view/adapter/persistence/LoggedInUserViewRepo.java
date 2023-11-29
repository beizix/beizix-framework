package org.beizix.admin.usecase.loggedinuser.view.adapter.persistence;

import org.beizix.core.config.adapter.persistence.entity.LoggedInUser;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggedInUserViewRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
