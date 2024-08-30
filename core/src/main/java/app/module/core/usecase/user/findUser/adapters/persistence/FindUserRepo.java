package app.module.core.usecase.user.findUser.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FindUserRepo extends JpaRepository<FrontUser, String> {}
