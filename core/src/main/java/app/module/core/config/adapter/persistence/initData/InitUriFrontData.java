package app.module.core.config.adapter.persistence.initData;

import java.io.IOException;
import java.util.Set;
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

@Component
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class InitUriFrontData implements ApplicationRunner {
  private final URISavePortIn uriSavePortIn;

  @Override
  public void run(ApplicationArguments args) throws IOException {
    if (!PropertyUtil.isCoreDataRequired()) return;

    final String FRONT = "uri.front";

    // 예제 게시판
    final String EXBOARD_LIST = "uri.front.board.exampleBoard";
    final String EXBOARD_VIEW = "uri.front.board.exampleBoard.{{pathVar}}";

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

    // 예제 게시판
    uriSavePortIn.connect(
        new URISaveCommand(
            EXBOARD_LIST,
            FRONT,
            AppType.FRONT,
            "/board/exampleBoard",
            true,
            "예제 게시판",
            null,
            "예제 게시판",
            "예제 게시판 목록",
            "front",
            null,
            Set.of("ROLE_GENERAL")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            EXBOARD_VIEW,
            EXBOARD_LIST,
            AppType.FRONT,
            "/board/exampleBoard/{{pathVar}}",
            false,
            "예제 게시판 보기",
            null,
            "예제 게시판 상세",
            "예제 게시판 상세",
            "view",
            null,
            Set.of("ROLE_GENERAL")),
        null,
        false);
  }
}
