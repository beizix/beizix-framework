package app.module.admin.usecase.role.remove.adapter.persistence;

import app.module.admin.config.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRemoveRepo extends JpaRepository<Role, String> {}
