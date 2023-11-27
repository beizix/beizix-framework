package org.beizix.admin.usecase.role.view.adapter.persistence;

import org.beizix.security.adapter.persistence.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleViewRepo extends JpaRepository<Role, String> {}
