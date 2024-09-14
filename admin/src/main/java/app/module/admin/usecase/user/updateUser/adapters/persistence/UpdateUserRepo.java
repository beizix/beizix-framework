package app.module.admin.usecase.user.updateUser.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateUserRepo extends JpaRepository<FrontUser, String> {}
