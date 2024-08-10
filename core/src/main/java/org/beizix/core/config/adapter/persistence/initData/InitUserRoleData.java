package org.beizix.core.config.adapter.persistence.initData;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.application.util.PropertyUtil;
import org.beizix.core.usecase.user.createRole.ports.CreateRolePortIn;
import org.beizix.core.usecase.user.createRole.ports.application.domain.CreateRoleCmd;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(2)
public class InitUserRoleData implements ApplicationRunner {
  private final CreateRolePortIn createRolePortIn;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    createRolePortIn.operate(
        new CreateRoleCmd("ROLE_ANONYMOUS", "익명 사용자", 0, Set.of("READ", "DOWNLOAD")));
  }
}
