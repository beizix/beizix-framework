package app.module.core.usecase.user.removeRoleWithPrivilege.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.UserRole;
import app.module.core.config.adapter.persistence.entity.UserRoleWithUserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveRoleWithPrivilegeRepo
    extends JpaRepository<UserRoleWithUserPrivilege, Integer> {
  void deleteAllByUserRole(UserRole userRole);
}
