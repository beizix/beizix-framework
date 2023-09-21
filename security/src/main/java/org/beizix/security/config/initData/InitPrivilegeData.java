package org.beizix.security.config.initData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.beizix.security.adapter.persistence.privilege.repository.PrivilegeRepo;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.utility.common.PropertyUtil;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitPrivilegeData implements ApplicationRunner {
  private final PrivilegeRepo privilegeRepo;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    privilegeRepo.save(
        Privilege.builder().id("READ_PRIVILEGE").build());

    privilegeRepo.save(
        Privilege.builder().id("WRITE_PRIVILEGE").build());

    privilegeRepo.save(
        Privilege.builder().id("DELETE_PRIVILEGE").build());

    privilegeRepo.save(
        Privilege.builder().id("UPDATE_PRIVILEGE").build());

    privilegeRepo.save(
        Privilege.builder().id("DOWNLOAD_PRIVILEGE").build());

    privilegeRepo.save(
        Privilege.builder().id("PRIVATE_DOWNLOAD_PRIVILEGE").build());
  }
}
