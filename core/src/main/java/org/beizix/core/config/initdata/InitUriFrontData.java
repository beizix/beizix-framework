package org.beizix.core.config.initdata;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.application.domain.uri.model.save.URIInput;
import org.beizix.core.application.port.in.uri.URISavePortIn;
import org.beizix.core.config.enums.AppType;
import org.beizix.utility.common.PropertyUtil;
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
        new URIInput(
            FRONT,
            null,
            AppType.FRONT,
            "/",
            true,
            "프론트",
            null,
            "org.beizix - 프론트 홈",
            "org.beizix 프론트 메인",
            "org.beizix,front",
            null,
            null),
        null,
        false);

    // 예제 게시판
    uriSavePortIn.connect(
        new URIInput(
            EXBOARD_LIST,
            FRONT,
            AppType.FRONT,
            "/board/exampleBoard",
            true,
            "예제 게시판",
            null,
            "org.beizix - 예제 게시판",
            "org.beizix 예제 게시판 목록",
            "org.beizix,front",
            null,
            null),
        null,
        false);

    uriSavePortIn.connect(
        new URIInput(
            EXBOARD_VIEW,
            EXBOARD_LIST,
            AppType.FRONT,
            "/board/exampleBoard/{{pathVar}}",
            false,
            "예제 게시판 보기",
            null,
            "org.beizix - 예제 게시판 상세",
            "org.beizix 예제 게시판 상세",
            "org.beizix,view",
            null,
            null),
        null,
        false);
  }
}
