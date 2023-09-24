package org.beizix.core.config.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.beizix.utility.common.PropertyUtil;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

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
                    ExBoardInput.builder()
                        .title(String.format("Title No.%s", idx))
                        .content(String.format("Content No.%s", idx))
                        .visible(true)
                        .boardStartDate(LocalDateTime.now())
                        .boardEndDate(LocalDateTime.now().plusMonths(2))
                        .createdBy("beizix-super")
                        .orderNo(idx)
                        .build()));
  }
}
