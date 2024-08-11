package org.beizix.core.usecase.user.removeRoleWithPrivilege.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.UserRole;
import org.beizix.core.config.adapter.persistence.entity.UserRoleWithUserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveRoleWithPrivilegeRepo
    extends JpaRepository<UserRoleWithUserPrivilege, Integer> {
  void deleteAllByUserRole(UserRole userRole);
}
