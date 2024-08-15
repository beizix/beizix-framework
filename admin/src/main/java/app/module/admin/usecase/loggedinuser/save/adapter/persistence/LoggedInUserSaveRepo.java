package app.module.admin.usecase.loggedinuser.save.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import app.module.core.config.adapter.persistence.entity.LoggedInUserEmbeddable;
import app.module.core.config.adapter.persistence.entity.LoggedInUser;

public interface LoggedInUserSaveRepo extends JpaRepository<LoggedInUser, LoggedInUserEmbeddable> {}
