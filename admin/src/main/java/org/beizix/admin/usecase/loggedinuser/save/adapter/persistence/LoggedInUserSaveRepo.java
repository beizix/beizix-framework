package org.beizix.admin.usecase.loggedinuser.save.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import org.beizix.core.config.adapter.persistence.entity.LoggedInUser;

public interface LoggedInUserSaveRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
