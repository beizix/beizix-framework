package app.module.admin.usecase.user.updateUser.adapters.persistence;

import app.module.admin.usecase.user.updateUser.ports.UpdateUserPortOut;
import app.module.admin.usecase.user.updateUser.ports.application.domain.UpdateUserCmd;
import app.module.core.config.adapter.persistence.entity.FrontUser;
import app.module.core.config.adapter.persistence.entity.UserRole;
import app.module.core.config.adapter.persistence.entity.UserWithUserRole;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UpdateUserDao implements UpdateUserPortOut {
  private final UpdateUserRepo updateUserRepo;
  private final GetUserPwdRepo getUserPwdRepo;

  @Override
  public void operate(UpdateUserCmd command) {
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
                            null, new FrontUser(command.getId()), new UserRole(role.getId())))
                .collect(Collectors.toSet())));
  }
}
