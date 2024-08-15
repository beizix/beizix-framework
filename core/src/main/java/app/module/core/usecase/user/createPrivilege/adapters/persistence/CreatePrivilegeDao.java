package app.module.core.usecase.user.createPrivilege.adapters.persistence;

import java.util.Optional;

import app.module.core.config.adapter.persistence.entity.UserPrivilege;
import app.module.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilegeCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.user.createPrivilege.ports.CreatePrivilegePortOut;
import app.module.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilege;
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
