package org.beizix.admin.usecase.role.remove.adapter.persistence;

import org.beizix.admin.configuration.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRemoveRepo extends JpaRepository<Role, String> {}
