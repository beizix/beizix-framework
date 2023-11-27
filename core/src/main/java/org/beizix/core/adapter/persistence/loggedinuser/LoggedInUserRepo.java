package org.beizix.core.adapter.persistence.loggedinuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.beizix.core.configuration.adapter.persistence.component.LoggedInUserEmbeddable;
import org.beizix.core.configuration.adapter.persistence.entity.LoggedInUser;

public interface LoggedInUserRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
