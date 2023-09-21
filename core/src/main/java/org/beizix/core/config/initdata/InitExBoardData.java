package org.beizix.core.config.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardCreateUpdateDao;
import org.beizix.utility.common.PropertyUtil;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitExBoardData implements ApplicationRunner {
  private final ExBoardCreateUpdateDao exBoardCreateUpdateDao;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    IntStream.range(0, 25)
        .forEach(
            idx ->
                exBoardCreateUpdateDao.operate(
                    ExBoard.builder()
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
