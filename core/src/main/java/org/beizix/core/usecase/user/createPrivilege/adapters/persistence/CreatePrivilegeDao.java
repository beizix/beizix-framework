package org.beizix.core.usecase.user.createPrivilege.adapters.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.UserPrivilege;
import org.beizix.core.usecase.user.createPrivilege.ports.CreatePrivilegePortOut;
import org.beizix.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilege;
import org.beizix.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilegeCmd;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreatePrivilegeDao implements CreatePrivilegePortOut {
  private final CreatePrivilegeRepo createPrivilegeRepo;

  @Override
  public Optional<CreatePrivilege> operate(CreatePrivilegeCmd command) {
    UserPrivilege userPrivilege =
        createPrivilegeRepo.save(
            new UserPrivilege(command.getId(), command.getDescription(), command.getOrderNo()));
    return Optional.of(
        new CreatePrivilege(
            userPrivilege.getId(), userPrivilege.getDescription(), userPrivilege.getOrderNo()));
  }
}
