package app.module.core.config.adapter.persistence.initData;

import java.util.Set;

import app.module.core.usecase.user.createRole.ports.application.domain.CreateRoleCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.config.application.util.PropertyUtil;
import app.module.core.usecase.user.createRole.ports.CreateRolePortIn;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class InitUserRoleData implements ApplicationRunner {
  private final CreateRolePortIn createRolePortIn;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    createRolePortIn.operate(
        new CreateRoleCmd(
            "ROLE_GENERAL",
            "일반 사용자",
            0,
            Set.of("READ", "WRITE", "DELETE", "UPDATE", "DOWNLOAD", "PRIVATE_DOWNLOAD")));
  }
}
