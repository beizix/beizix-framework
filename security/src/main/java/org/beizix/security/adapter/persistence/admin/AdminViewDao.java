package org.beizix.security.adapter.persistence.admin;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;
import org.beizix.security.application.domain.admin.model.view.PrivilegeOutput;
import org.beizix.security.application.domain.admin.model.view.RoleOutput;
import org.beizix.security.application.port.out.admin.AdminViewPortOut;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AdminViewDao implements AdminViewPortOut {
  private final AdminRepo adminRepo;

  @Override
  @Transactional
  public Optional<AdminViewOutput> connect(String id) {
    return adminRepo.findAdminUserById(id).stream()
        .map(
            admin ->
                new AdminViewOutput(
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
