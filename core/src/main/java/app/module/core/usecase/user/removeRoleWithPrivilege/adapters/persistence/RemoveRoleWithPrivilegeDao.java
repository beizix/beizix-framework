package app.module.core.usecase.user.removeRoleWithPrivilege.adapters.persistence;

import app.module.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.persistence.entity.UserRole;
import app.module.core.usecase.user.removeRoleWithPrivilege.ports.RemoveRoleWithPrivilegePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class RemoveRoleWithPrivilegeDao implements RemoveRoleWithPrivilegePortOut {
  private final RemoveRoleWithPrivilegeRepo removeRoleWithPrivilegeRepo;

  @Override
  public void operate(RemoveRoleWithPrivilegeCmd command) {
    removeRoleWithPrivilegeRepo.deleteAllByUserRole(new UserRole(command.getRoleId()));
  }
}
