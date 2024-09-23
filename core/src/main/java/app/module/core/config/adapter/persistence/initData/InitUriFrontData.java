package app.module.core.config.adapter.persistence.initData;

import java.io.IOException;
import java.util.Set;

import app.module.core.config.adapter.persistence.initData.helper.RemoveAllFrontURIRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.util.PropertyUtil;
import app.module.core.usecase.uri.save.application.domain.URISaveCommand;
import app.module.core.usecase.uri.save.application.port.in.URISavePortIn;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class InitUriFrontData implements ApplicationRunner {
  private final URISavePortIn uriSavePortIn;
  private final RemoveAllFrontURIRepo removeAllFrontURIRepo;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws IOException {
    if (!PropertyUtil.isCoreDataRequired()) return;

    removeAllFrontURIRepo.deleteAllByAppType(AppType.FRONT);

    final String FRONT = "uri.front";

    uriSavePortIn.connect(
        new URISaveCommand(
            FRONT,
            null,
            AppType.FRONT,
            "/",
            true,
            "프론트",
            null,
            "프론트 홈",
            "프론트 메인",
            "front",
            null,
            null),
        null,
        false);

    // 로그인
    uriSavePortIn.connect(
        new URISaveCommand(
            FRONT + ".login",
            FRONT,
            AppType.FRONT,
            "/login",
            true,
            "프론트",
            null,
            "로그인",
            "로그인 페이지",
            "front",
            null,
            null),
        null,
        false);
  }
}
