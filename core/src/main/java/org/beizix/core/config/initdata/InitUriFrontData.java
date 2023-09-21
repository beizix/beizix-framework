package org.beizix.core.config.initdata;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URICreateUpdateService;
import org.beizix.utility.common.PropertyUtil;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class InitUriFrontData implements ApplicationRunner {
  private final URICreateUpdateService uriCreateUpdateService;

  @Override
  public void run(ApplicationArguments args) throws IOException {
    if (!PropertyUtil.isCoreDataRequired()) return;

    final String FRONT = "uri.front";

    // 예제 게시판
    final String EXBOARD_LIST = "uri.front.board.exampleBoard";
    final String EXBOARD_VIEW = "uri.front.board.exampleBoard.{{pathVars}}";

    uriCreateUpdateService.operate(
        URI.builder()
            .appType(AppType.FRONT)
            .parentId(null)
            .id(FRONT)
            .uri("/")
            .text("프론트")
            .showOnNavi(true)
            .ogTitle("org.beizix - 프론트 홈")
            .ogDesc("org.beizix 프론트 메인")
            .ogKeywords("org.beizix,front")
            .build(),
        false);

    // 예제 게시판
    uriCreateUpdateService.operate(
        URI.builder()
            .appType(AppType.FRONT)
            .parentId(FRONT)
            .id(EXBOARD_LIST)
            .uri("/board/exampleBoard")
            .text("예제 게시판")
            .showOnNavi(true)
            .ogTitle("org.beizix - 예제 게시판")
            .ogDesc("org.beizix 예제 게시판 목록")
            .ogKeywords("org.beizix,front")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .appType(AppType.FRONT)
            .parentId(EXBOARD_LIST)
            .id(EXBOARD_VIEW)
            .uri("/board/exampleBoard/{{pathVars}}")
            .text("예제 게시판 보기")
            .showOnNavi(false)
            .ogTitle("org.beizix - 예제 게시판 상세")
            .ogDesc("org.beizix 예제 게시판 상세")
            .ogKeywords("org.beizix,view")
            .build(),
        false);
  }
}
