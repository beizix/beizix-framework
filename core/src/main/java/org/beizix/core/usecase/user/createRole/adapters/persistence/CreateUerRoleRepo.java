package org.beizix.core.usecase.user.createRole.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateUerRoleRepo extends JpaRepository<UserRole, String> {}
