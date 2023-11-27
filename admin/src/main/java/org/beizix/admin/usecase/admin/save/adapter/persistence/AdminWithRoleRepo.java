package org.beizix.admin.usecase.admin.save.adapter.persistence;

import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminWithRoleRepo extends JpaRepository<AdminWithRole, Long> {
  void deleteAllByAdminId(String adminId);
}