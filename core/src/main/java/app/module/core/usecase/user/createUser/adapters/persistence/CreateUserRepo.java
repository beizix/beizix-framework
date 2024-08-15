package app.module.core.usecase.user.createUser.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateUserRepo extends JpaRepository<FrontUser, String> {}
