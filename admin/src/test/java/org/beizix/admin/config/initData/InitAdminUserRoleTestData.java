package org.beizix.admin.config.initData;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.role.save.adapter.persistence.RoleSaveRepo;
import org.beizix.admin.config.adapter.persistence.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitAdminUserRoleTestData implements ApplicationRunner {
  private final RoleSaveRepo roleRepo;
  private final Environment env;

  @Override
  public void run(ApplicationArguments args) {
    if (!env.getProperty("beizix.initData.test", Boolean.class)) return;

    roleRepo.save(new Role("ROLE_SUPER"));
    roleRepo.save(new Role("ROLE_STAFF"));
  }
}
