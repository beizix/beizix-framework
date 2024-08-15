package app.module.front.usecase.user.find.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FindUserRepo extends JpaRepository<FrontUser, String> {}
