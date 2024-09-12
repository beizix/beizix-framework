package app.module.core.config.adapter.persistence.initData;

import app.module.core.config.adapter.persistence.initData.helper.FrontUserHelperRepo;
import app.module.core.config.application.util.PropertyUtil;
import app.module.core.usecase.user.createUser.ports.CreateUserPortIn;
import app.module.core.usecase.user.createUser.ports.application.domain.CreateUserCmd;
import java.util.Set;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  private final FrontUserHelperRepo frontUserHelperRepo;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    frontUserHelperRepo.deleteById("generalUser");

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
