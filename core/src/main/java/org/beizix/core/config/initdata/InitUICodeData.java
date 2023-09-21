package org.beizix.core.config.initdata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.application.service.UICodeCreateUpdateService;
import org.beizix.utility.common.PropertyUtil;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitUICodeData implements ApplicationRunner {
  private final UICodeCreateUpdateService uiCodeCreateUpdateService;

  @Override
  public void run(ApplicationArguments args) {
    if (!PropertyUtil.isCoreDataRequired()) return;

    final String UICODE = "code";
    final String UICODE_PAGEABLE = "code.pageable";
    final String UICODE_PAGEABLE_ROWS = "code.pageable.rows";

    // root 노드 - code
    uiCodeCreateUpdateService.operate(
        new UICode(UICODE, null, null, "UI Code", "", null, true, null, null), false);

    // code - board
    uiCodeCreateUpdateService.operate(
        new UICode(UICODE_PAGEABLE, UICODE, 0, "Pageable", "", null, true, null, null), false);

    // code - board - rows
    uiCodeCreateUpdateService.operate(
        new UICode(UICODE_PAGEABLE_ROWS, UICODE_PAGEABLE, 0, "ROW 갯수", "", null, true, null, null),
        false);

    uiCodeCreateUpdateService.operate(
        new UICode(
            UICODE_PAGEABLE_ROWS + ".10",
            UICODE_PAGEABLE_ROWS,
            1,
            "10",
            "10",
            null,
            true,
            null,
            null),
        false);

    uiCodeCreateUpdateService.operate(
        new UICode(
            UICODE_PAGEABLE_ROWS + ".20",
            UICODE_PAGEABLE_ROWS,
            2,
            "20",
            "20",
            null,
            true,
            null,
            null),
        false);

    uiCodeCreateUpdateService.operate(
        new UICode(
            UICODE_PAGEABLE_ROWS + ".50",
            UICODE_PAGEABLE_ROWS,
            3,
            "50",
            "50",
            null,
            true,
            null,
            null),
        false);

    uiCodeCreateUpdateService.operate(
        new UICode(
            UICODE_PAGEABLE_ROWS + ".100",
            UICODE_PAGEABLE_ROWS,
            4,
            "100",
            "100",
            null,
            true,
            null,
            null),
        false);
  }
}
