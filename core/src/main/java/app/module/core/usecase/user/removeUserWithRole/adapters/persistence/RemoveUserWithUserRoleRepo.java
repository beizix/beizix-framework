package app.module.core.usecase.user.removeUserWithRole.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import app.module.core.config.adapter.persistence.entity.UserWithUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveUserWithUserRoleRepo extends JpaRepository<UserWithUserRole, Long> {
  void deleteAllByFrontUser(FrontUser frontUser);
}
