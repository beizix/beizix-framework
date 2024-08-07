package org.beizix.admin.config.adapter.persistence.initdata;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.admin.usecase.admin.save.ports.AdminSavePortIn;
import org.beizix.core.config.application.util.PropertyUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
        "beizix-super",
        "beizix-framework",
        "super@beizix.org",
        false,
        false,
        List.of("ROLE_SUPER"));

    adminSavePortIn.connect(
        "beizix-manager",
        "beizix-framework",
        "manager@beizix.org",
        false,
        false,
        List.of("ROLE_MANAGER"));

    adminSavePortIn.connect(
        "beizix-staff",
        "beizix-framework",
        "staff@beizix.org",
        false,
        false,
        List.of("ROLE_STAFF"));
  }
}
