package app.module.admin.usecase.role.list.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleListRepo extends JpaRepository<Role, String>{}
