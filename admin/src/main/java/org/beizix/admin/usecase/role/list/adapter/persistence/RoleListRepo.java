package org.beizix.admin.usecase.role.list.adapter.persistence;

import org.beizix.admin.config.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleListRepo extends JpaRepository<Role, String>{}
