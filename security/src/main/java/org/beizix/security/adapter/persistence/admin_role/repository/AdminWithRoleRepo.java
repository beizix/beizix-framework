package org.beizix.security.adapter.persistence.admin_role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole;

public interface AdminWithRoleRepo extends JpaRepository<AdminWithRole, Long> {}
