package org.beizix.admin.usecase.admin.view.adapter.persistence;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.view.application.domain.AdminView;
import org.beizix.admin.usecase.admin.view.application.port.out.AdminViewPortOut;
import org.beizix.admin.config.adapter.persistence.entity.Privilege;
import org.beizix.admin.config.adapter.persistence.entity.Role;
import org.beizix.admin.usecase.admin.view.application.domain.PrivilegeView;
import org.beizix.admin.usecase.admin.view.application.domain.RoleView;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AdminViewDao implements AdminViewPortOut {
  private final AdminViewRepo adminRepo;

  @Override
  @Transactional
  public Optional<AdminView> connect(String id) {
    return adminRepo.findAdminUserById(id).stream()
        .map(
            admin ->
                new AdminView(
                    admin.getCreatedAt(),
                    admin.getCreatedBy(),
                    admin.getUpdatedAt(),
                    admin.getUpdatedBy(),
                    admin.getId(),
                    admin.getPassword(),
                    admin.getEmail(),
                    admin.getPasswordUpdatedAt(),
                    admin.getAccountDisabled(),
                    admin.getLoginFailCnt(),
                    admin.getAccountLocked(),
                    admin.getWithRoles().stream()
                        .map(
                            withRole -> {
                              Role role = withRole.getRole();
                              return new RoleView(
                                  role.getCreatedAt(),
                                  role.getCreatedBy(),
                                  role.getUpdatedAt(),
                                  role.getUpdatedBy(),
                                  role.getId(),
                                  role.getDescription(),
                                  role.getOrderNo(),
                                  role.getWithPrivileges().stream()
                                      .map(
                                          withPrivilege -> {
                                            Privilege privilege = withPrivilege.getPrivilege();
                                            return new PrivilegeView(
                                                privilege.getCreatedAt(),
                                                privilege.getCreatedBy(),
                                                privilege.getUpdatedAt(),
                                                privilege.getUpdatedBy(),
                                                privilege.getId());
                                          })
                                      .collect(Collectors.toList()));
                            })
                        .collect(Collectors.toList())))
        .findFirst();
  }
}
