package app.module.admin.usecase.loggedinuser.view.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.LoggedInUser;
import app.module.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggedInUserViewRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
