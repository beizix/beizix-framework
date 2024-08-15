package org.beizix.front.usecase.user.find.adapters.persistence;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.FrontUser;
import org.beizix.front.usecase.user.find.ports.FindUserPortOut;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUser;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserCmd;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserPrivilege;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserRole;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class FindUserDao implements FindUserPortOut {
  private final FindUserRepo findUserRepo;

  @Override
  @Transactional
  public Optional<FindUser> operate(FindUserCmd command) {
    Optional<FrontUser> userOpt = findUserRepo.findById(command.getId());
    return userOpt.isPresent()
        ? userOpt.map(
            frontUser ->
                new FindUser(
                    frontUser.getId(),
                    frontUser.getPassword(),
                    frontUser.getEmail(),
                    frontUser.getPasswordUpdatedAt(),
                    frontUser.getAccountDisabled(),
                    frontUser.getLoginFailCnt(),
                    frontUser.getAccountLocked(),
                    frontUser.getWithUserRoles().stream()
                        .map(
                            withRole ->
                                new FindUserRole(
                                    withRole.getRole().getId(),
                                    withRole.getRole().getDescription(),
                                    withRole.getRole().getOrderNo(),
                                    withRole.getRole().getWithUserPrivileges().stream()
                                        .map(
                                            withPrivilege ->
                                                new FindUserPrivilege(
                                                    withPrivilege.getUserPrivilege().getId(),
                                                    withPrivilege
                                                        .getUserPrivilege()
                                                        .getDescription(),
                                                    withPrivilege.getUserPrivilege().getOrderNo()))
                                        .collect(Collectors.toSet())))
                        .collect(Collectors.toSet())))
        : Optional.empty();
  }
}
