package app.module.admin.config.adapter.persistence.initdata;

import java.util.List;

import app.module.admin.usecase.admin.save.ports.AdminSavePortIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.module.core.config.application.util.PropertyUtil;
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
        "superuser",
        "super1@#$",
        "super@framework.org",
        false,
        false,
        List.of("ROLE_SUPER"));

    adminSavePortIn.connect(
        "manager",
        "manager1@#$",
        "manager@framework.org",
        false,
        false,
        List.of("ROLE_MANAGER"));

    adminSavePortIn.connect(
        "staff",
        "staff1@#$",
        "staff@framework.org",
        false,
        false,
        List.of("ROLE_STAFF"));
  }
}
