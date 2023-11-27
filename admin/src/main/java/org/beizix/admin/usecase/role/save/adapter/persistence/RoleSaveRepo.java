package org.beizix.admin.usecase.role.save.adapter.persistence;

import org.beizix.security.adapter.persistence.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleSaveRepo extends JpaRepository<Role, String> {}
