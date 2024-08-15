package org.beizix.core.usecase.user.createRole.adapters.persistence;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.UserPrivilege;
import org.beizix.core.config.adapter.persistence.entity.UserRole;
import org.beizix.core.config.adapter.persistence.entity.UserRoleWithUserPrivilege;
import org.beizix.core.usecase.user.createRole.ports.CreateRolePortOut;
import org.beizix.core.usecase.user.createRole.ports.application.domain.CreateRole;
import org.beizix.core.usecase.user.createRole.ports.application.domain.CreateRoleCmd;
import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.RemoveRoleWithPrivilegePortIn;
import org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.application.domain.RemoveRoleWithPrivilegeCmd;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
class CreateRoleDao implements CreateRolePortOut {
  private final CreateUerRoleRepo createUerRoleRepo;
  private final RemoveRoleWithPrivilegePortIn removeRoleWithPrivilegePortIn;

  @Override
  @Transactional
  public Optional<CreateRole> operate(CreateRoleCmd command) {
    removeRoleWithPrivilegePortIn.operate(new RemoveRoleWithPrivilegeCmd(command.getId()));

    UserRole userRole =
        createUerRoleRepo.save(
            new UserRole(
                command.getId(),
                command.getDescription(),
                command.getOrderNo(),
                command.getPrivileges().stream()
                    .map(
                        s ->
                            new UserRoleWithUserPrivilege(
                                null,
                                // CascadeType.MERGE 이기에 가능하다.
                                new UserRole(command.getId()),
                                new UserPrivilege(s)))
                    .collect(Collectors.toSet())));

    return Optional.of(
        new CreateRole(
            userRole.getId(),
            userRole.getDescription(),
            userRole.getOrderNo(),
            command.getPrivileges()));
  }
}