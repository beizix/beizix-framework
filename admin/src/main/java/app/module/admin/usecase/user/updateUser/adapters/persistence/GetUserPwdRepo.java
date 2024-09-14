package app.module.admin.usecase.user.updateUser.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetUserPwdRepo extends JpaRepository<FrontUser, String> {}
