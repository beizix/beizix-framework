package app.module.admin.usecase.user.getRoles.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GetRolesRepo
    extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole> {}
