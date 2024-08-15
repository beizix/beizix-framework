package org.beizix.core.usecase.user.createUser.adapters.persistence;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.FrontUser;
import org.beizix.core.config.adapter.persistence.entity.UserRole;
import org.beizix.core.config.adapter.persistence.entity.UserWithUserRole;
import org.beizix.core.usecase.user.createUser.ports.CreateUserPortOut;
import org.beizix.core.usecase.user.createUser.ports.application.domain.CreateUser;
import org.beizix.core.usecase.user.createUser.ports.application.domain.CreateUserCmd;
import org.beizix.core.usecase.user.removeUserWithRole.ports.RemoveUserWithRolePortIn;
import org.beizix.core.usecase.user.removeUserWithRole.ports.application.domain.RemoveUserWithRoleCmd;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
class CreateUserDao implements CreateUserPortOut {
  private final CreateUserRepo createUserRepo;
  private final RemoveUserWithRolePortIn removeUserWithRolePortIn;

  @Override
  @Transactional
  public Optional<CreateUser> operate(CreateUserCmd command) {
    removeUserWithRolePortIn.operate(new RemoveUserWithRoleCmd("generalUser"));

    FrontUser frontUser =
        createUserRepo.save(
            new FrontUser(
                command.getId(),
                command.getPassword(),
                command.getEmail(),
                LocalDateTime.now(),
                command.isAccountDisabled(),
                command.getLoginFailCnt(),
                command.getAccountLocked(),
                command.getRoles().stream()
                    .map(
                        s ->
                            new UserWithUserRole(
                                null, new FrontUser(command.getId()), new UserRole(s)))
                    .collect(Collectors.toSet())));

    return Optional.of(
        new CreateUser(
            frontUser.getId(),
            frontUser.getPassword(),
            frontUser.getEmail(),
            frontUser.getAccountDisabled(),
            frontUser.getLoginFailCnt(),
            frontUser.getAccountLocked(),
            command.getRoles()));
  }
}
