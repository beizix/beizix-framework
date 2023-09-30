package org.beizix.core.config.initdata;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.beizix.utility.common.PropertyUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitExBoardData implements ApplicationRunner {
  private final ExBoardSavePortOut exBoardSavePortOut;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    IntStream.range(0, 25)
        .forEach(
            idx ->
                exBoardSavePortOut.connect(
                    new ExBoardSaveInput()
                        .setTitle(String.format("Title No.%s", idx))
                        .setContent(String.format("Content No.%s", idx))
                        .setVisible(true)
                        .setBoardStartDate(LocalDateTime.now())
                        .setBoardEndDate(LocalDateTime.now().plusMonths(2))
                        .setOrderNo(idx)));
  }
}
