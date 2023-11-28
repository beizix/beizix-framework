package org.beizix.admin.usecase.role.view.adapter.persistence;

import org.beizix.admin.config.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleViewRepo extends JpaRepository<Role, String> {}
