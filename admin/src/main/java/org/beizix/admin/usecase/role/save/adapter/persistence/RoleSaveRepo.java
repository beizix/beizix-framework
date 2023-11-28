package org.beizix.admin.usecase.role.save.adapter.persistence;

import org.beizix.admin.config.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleSaveRepo extends JpaRepository<Role, String> {}
