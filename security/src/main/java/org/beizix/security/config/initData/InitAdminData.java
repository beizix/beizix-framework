package org.beizix.security.config.initData;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReferInput;
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveInput;
import org.beizix.security.application.domain.role.model.save.RoleSaveReferInput;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.utility.common.PropertyUtil;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class InitAdminData implements ApplicationRunner {
  private final AdminSavePortIn adminSavePortIn;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    adminSavePortIn.connect(
        AdminSaveInput.builder()
            .id("beizix-super")
            .password("beizix-framework")
            .email("super@beizix.org")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveInput.builder()
                        .admin(new AdminSaveReferInput("beizix-super"))
                        .role(new RoleSaveReferInput("ROLE_SUPER"))
                        .build()))
            .build());

    adminSavePortIn.connect(
        AdminSaveInput.builder()
            .id("beizix-manager")
            .password("beizix-framework")
            .email("manager@beizix.org")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveInput.builder()
                        .admin(new AdminSaveReferInput("beizix-manager"))
                        .role(new RoleSaveReferInput("ROLE_MANAGER"))
                        .build()))
            .build());

    adminSavePortIn.connect(
        AdminSaveInput.builder()
            .id("beizix-staff")
            .password("beizix-framework")
            .email("staff@beizix.org")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveInput.builder()
                        .admin(new AdminSaveReferInput("beizix-staff"))
                        .role(new RoleSaveReferInput("ROLE_STAFF"))
                        .build()))
            .build());
  }
}
