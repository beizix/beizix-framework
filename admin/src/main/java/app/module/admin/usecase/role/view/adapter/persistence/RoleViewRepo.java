package app.module.admin.usecase.role.view.adapter.persistence;

import app.module.admin.config.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleViewRepo extends JpaRepository<Role, String> {}
