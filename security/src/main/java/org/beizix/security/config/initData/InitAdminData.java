package org.beizix.security.config.initData;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.beizix.security.application.domain.admin.model.save.AdminSaveMinimumReq;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveReq;
import org.beizix.security.application.domain.role.model.save.RoleSaveMinimumReq;
import org.beizix.security.application.port.in.admin.AdminSaveService;
import org.beizix.utility.common.PropertyUtil;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class InitAdminData implements ApplicationRunner {
  private final AdminSaveService adminSaveService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    adminSaveService.operate(
        AdminSaveReq.builder()
            .id("beizix-super")
            .password("beizix-framework")
            .email("super@beizix.org")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveReq.builder()
                        .admin(new AdminSaveMinimumReq("beizix-super"))
                        .role(new RoleSaveMinimumReq("ROLE_SUPER"))
                        .build()))
            .build());

    adminSaveService.operate(
        AdminSaveReq.builder()
            .id("beizix-manager")
            .password("beizix-framework")
            .email("manager@beizix.org")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveReq.builder()
                        .admin(new AdminSaveMinimumReq("beizix-manager"))
                        .role(new RoleSaveMinimumReq("ROLE_MANAGER"))
                        .build()))
            .build());

    adminSaveService.operate(
        AdminSaveReq.builder()
            .id("beizix-staff")
            .password("beizix-framework")
            .email("staff@beizix.org")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveReq.builder()
                        .admin(new AdminSaveMinimumReq("beizix-staff"))
                        .role(new RoleSaveMinimumReq("ROLE_STAFF"))
                        .build()))
            .build());
  }
}
