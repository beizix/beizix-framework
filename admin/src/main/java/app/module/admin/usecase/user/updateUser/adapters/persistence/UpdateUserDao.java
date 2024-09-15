package app.module.admin.usecase.user.updateUser.adapters.persistence;

import app.module.admin.usecase.user.updateUser.ports.UpdateUserPortOut;
import app.module.admin.usecase.user.updateUser.ports.application.domain.UpdateUserCmd;
import app.module.core.config.adapter.persistence.entity.FrontUser;
import app.module.core.config.adapter.persistence.entity.UserRole;
import app.module.core.config.adapter.persistence.entity.UserWithUserRole;
import java.util.stream.Collectors;

import app.module.core.usecase.user.removeUserWithRole.ports.RemoveUserWithRolePortIn;
import app.module.core.usecase.user.removeUserWithRole.ports.application.domain.RemoveUserWithRoleCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
class UpdateUserDao implements UpdateUserPortOut {
  private final UpdateUserRepo updateUserRepo;
  private final GetUserPwdRepo getUserPwdRepo;
  private final RemoveUserWithRolePortIn removeUserWithRolePortIn;

  @Override
  @Transactional
  public void operate(UpdateUserCmd command) {
    removeUserWithRolePortIn.operate(new RemoveUserWithRoleCmd(command.getId()));

    FrontUser getUser = getUserPwdRepo.findById(command.getId()).orElseThrow();

    updateUserRepo.save(
        new FrontUser(
            command.getId(),
            getUser.getPassword(),
            command.getEmail(),
            getUser.getPasswordUpdatedAt(),
            command.getAccountDisabled(),
            command.getLoginFailCnt(),
            command.getAccountLocked(),
            command.getRoles().stream()
                .map(
                    role ->
                        new UserWithUserRole(
                            null, new FrontUser(command.getId()), new UserRole(role)))
                .collect(Collectors.toSet())));
  }
}
