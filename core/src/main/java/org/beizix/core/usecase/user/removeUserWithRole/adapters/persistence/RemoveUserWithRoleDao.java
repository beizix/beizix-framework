package org.beizix.core.usecase.user.removeUserWithRole.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.FrontUser;
import org.beizix.core.usecase.user.removeUserWithRole.ports.RemoveUserWithRolePortOut;
import org.beizix.core.usecase.user.removeUserWithRole.ports.application.domain.RemoveUserWithRoleCmd;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class RemoveUserWithRoleDao implements RemoveUserWithRolePortOut {
  private final RemoveUserWithUserRoleRepo removeUserWithUserRoleRepo;

  @Override
  public void operate(RemoveUserWithRoleCmd command) {
    removeUserWithUserRoleRepo.deleteAllByFrontUser(new FrontUser(command.getUserId()));
  }
}
