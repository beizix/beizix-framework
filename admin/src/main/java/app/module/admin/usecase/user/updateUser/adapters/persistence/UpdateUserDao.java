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

  @Override
  public void operate(UpdateUserCmd command) {
    updateUserRepo.save(
        new FrontUser(
            command.getId(),
            command.getPassword(),
            command.getEmail(),
            command.getPasswordUpdatedAt(),
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
