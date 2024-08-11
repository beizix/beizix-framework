package org.beizix.core.config.adapter.persistence.initData;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.config.application.util.PropertyUtil;
import org.beizix.core.usecase.user.createUser.ports.CreateUserPortIn;
import org.beizix.core.usecase.user.createUser.ports.application.domain.CreateUserCmd;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class InitFrontUserData implements ApplicationRunner {
  private final CreateUserPortIn createUserPortIn;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    createUserPortIn.operate(
        new CreateUserCmd(
            "generalUser",
            "generalUser1@#$",
            "generalUser@test.com",
            false,
            0,
            false,
            Set.of("ROLE_GENERAL")));
  }
}
