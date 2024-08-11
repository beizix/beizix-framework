package org.beizix.core.usecase.user.removeRoleWithPrivilege.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.UserRole;
import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;
import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.RemoveRoleWithPrivilegePortOut;
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
