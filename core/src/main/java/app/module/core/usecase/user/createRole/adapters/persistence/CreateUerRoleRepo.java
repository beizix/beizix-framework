package app.module.core.usecase.user.createRole.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateUerRoleRepo extends JpaRepository<UserRole, String> {}
