package app.module.admin.usecase.role.save.adapter.persistence;

import app.module.admin.config.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleSaveRepo extends JpaRepository<Role, String> {}
