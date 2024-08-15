package app.module.admin.usecase.loggedinuser.remove.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import app.module.core.config.adapter.persistence.entity.LoggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggedInUserRemoveRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
