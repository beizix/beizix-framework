package org.beizix.core.usecase.user.createRole.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.UserPrivilege;
import org.beizix.core.config.adapter.persistence.entity.UserRole;
import org.beizix.core.config.adapter.persistence.entity.UserRoleWithUserPrivilege;
import org.beizix.core.usecase.user.createRole.ports.application.domain.CreateRole;
import org.beizix.core.usecase.user.createRole.ports.application.domain.CreateRoleCmd;
import org.beizix.core.usecase.user.createRole.ports.CreateRolePortOut;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class CreateRoleDao implements CreateRolePortOut {
  private final CreateUerRoleRepo createUerRoleRepo;

  @Override
  public Optional<CreateRole> operate(CreateRoleCmd command) {
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
                                null, new UserRole(command.getId()), new UserPrivilege(s)))
                    .collect(Collectors.toSet())));

    return Optional.of(
        new CreateRole(
            userRole.getId(),
            userRole.getDescription(),
            userRole.getOrderNo(),
            userRole.getWithUserPrivileges().stream()
                .map(
                    userRoleWithUserPrivilege ->
                        userRoleWithUserPrivilege.getUserPrivilege().getId())
                .collect(Collectors.toSet())));
  }
}
