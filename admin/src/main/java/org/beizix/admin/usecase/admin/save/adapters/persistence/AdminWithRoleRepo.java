package org.beizix.admin.usecase.admin.save.adapters.persistence;

import org.beizix.admin.config.adapter.persistence.entity.AdminWithRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminWithRoleRepo extends JpaRepository<AdminWithRole, Long> {
  void deleteAllByAdminId(String adminId);
}
