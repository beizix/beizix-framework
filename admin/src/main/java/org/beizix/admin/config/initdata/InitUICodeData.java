package org.beizix.admin.config.initdata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.admin.usecase.uicode.save.application.port.in.UICodeSaveCommand;
import org.beizix.admin.usecase.uicode.save.application.port.in.UICodeSavePortIn;
import org.beizix.utility.common.PropertyUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitUICodeData implements ApplicationRunner {
  private final UICodeSavePortIn uiCodeSavePortIn;

  @Override
  public void run(ApplicationArguments args) {
    if (!PropertyUtil.isCoreDataRequired()) return;

    final String UICODE = "code";
    final String UICODE_PAGEABLE = "code.pageable";
    final String UICODE_PAGEABLE_ROWS = "code.pageable.rows";

    // root 노드 - code
    uiCodeSavePortIn.connect(
        new UICodeSaveCommand(UICODE, null, null, "UI Code", "", null, true), false);

    // code - board
    uiCodeSavePortIn.connect(
        new UICodeSaveCommand(UICODE_PAGEABLE, UICODE, 0, "Pageable", "", null, true), false);

    // code - board - rows
    uiCodeSavePortIn.connect(
        new UICodeSaveCommand(UICODE_PAGEABLE_ROWS, UICODE_PAGEABLE, 0, "ROW 갯수", "", null, true),
        false);

    uiCodeSavePortIn.connect(
        new UICodeSaveCommand(
            UICODE_PAGEABLE_ROWS + ".10", UICODE_PAGEABLE_ROWS, 1, "10", "10", null, true),
        false);

    uiCodeSavePortIn.connect(
        new UICodeSaveCommand(
            UICODE_PAGEABLE_ROWS + ".20", UICODE_PAGEABLE_ROWS, 2, "20", "20", null, true),
        false);

    uiCodeSavePortIn.connect(
        new UICodeSaveCommand(
            UICODE_PAGEABLE_ROWS + ".50", UICODE_PAGEABLE_ROWS, 3, "50", "50", null, true),
        false);

    uiCodeSavePortIn.connect(
        new UICodeSaveCommand(
            UICODE_PAGEABLE_ROWS + ".100", UICODE_PAGEABLE_ROWS, 4, "100", "100", null, true),
        false);
  }
}
