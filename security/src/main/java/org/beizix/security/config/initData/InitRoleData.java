package org.beizix.security.config.initData;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.adapter.persistence.role_privilege.model.RoleWithPrivilege;
import org.beizix.utility.common.PropertyUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class InitRoleData implements ApplicationRunner {
  private final RoleRepo roleRepo;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    roleRepo.save(
        Role.builder()
            .id("ROLE_SUPER")
            .description("SUPER 관리자")
            .withPrivileges(
                Set.of(
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_SUPER").build())
                        .privilege(
                            Privilege.builder().id("READ").build())
                        .build(),
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_SUPER").build())
                        .privilege(
                            Privilege.builder().id("WRITE").build())
                        .build(),
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_SUPER").build())
                        .privilege(
                            Privilege.builder()
                                .id("DELETE")
                                .build())
                        .build(),
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_SUPER").build())
                        .privilege(
                            Privilege.builder()
                                .id("UPDATE")
                                .build())
                        .build(),
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_SUPER").build())
                        .privilege(
                            Privilege.builder()
                                .id("DOWNLOAD")
                                .build())
                        .build()))
            .orderNo(0)
            .build());

    roleRepo.save(
        Role.builder()
            .id("ROLE_MANAGER")
            .description("매니저")
            .withPrivileges(
                Set.of(
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_MANAGER").build())
                        .privilege(
                            Privilege.builder().id("READ").build())
                        .build(),
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_MANAGER").build())
                        .privilege(
                            Privilege.builder().id("WRITE").build())
                        .build(),
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_MANAGER").build())
                        .privilege(
                            Privilege.builder()
                                .id("DOWNLOAD")
                                .build())
                        .build()))
            .orderNo(1)
            .build());

    roleRepo.save(
        Role.builder()
            .id("ROLE_STAFF")
            .description("스텝")
            .withPrivileges(
                Set.of(
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_STAFF").build())
                        .privilege(
                            Privilege.builder().id("READ").build())
                        .build(),
                    RoleWithPrivilege.builder()
                        .role(Role.builder().id("ROLE_STAFF").build())
                        .privilege(
                            Privilege.builder()
                                .id("PRIVATE_DOWNLOAD")
                                .build())
                        .build()))
            .orderNo(2)
            .build());
  }
}
