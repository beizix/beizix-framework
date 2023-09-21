package org.beizix.admin.config.initData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.adapter.persistence.role.model.Role;

@Component
@RequiredArgsConstructor
public class InitAdminUserRoleTestData implements ApplicationRunner {
  private final RoleRepo roleRepo;
  private final Environment env;

  @Override
  public void run(ApplicationArguments args) {
    if (!env.getProperty("beizix.initData.test", Boolean.class)) return;

    roleRepo.save(Role.builder().id("ROLE_SUPER").build());
    roleRepo.save(Role.builder().id("ROLE_STAFF").build());
  }
}
