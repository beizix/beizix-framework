package org.beizix.core.usecase.user.removeUserWithRole.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.FrontUser;
import org.beizix.core.config.adapter.persistence.entity.UserWithUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveUserWithUserRoleRepo extends JpaRepository<UserWithUserRole, Long> {
  void deleteAllByFrontUser(FrontUser frontUser);
}
