package org.beizix.admin.usecase.loggedinuser.view.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.LoggedInUser;
import org.beizix.core.configuration.adapter.persistence.component.LoggedInUserEmbeddable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggedInUserViewRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
