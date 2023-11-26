package org.beizix.admin.usecase.admin.view.adapter.persistence;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.view.application.domain.AdminView;
import org.beizix.admin.usecase.admin.view.application.port.out.AdminViewPortOut;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.application.domain.admin.model.view.PrivilegeOutput;
import org.beizix.security.application.domain.admin.model.view.RoleOutput;
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
                              return new RoleOutput(
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
                                            return new PrivilegeOutput(
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
