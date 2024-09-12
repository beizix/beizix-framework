package app.module.admin.config.adapter.persistence.initdata;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import app.module.admin.config.adapter.persistence.initdata.helper.RemoveExBoardAllRepo;
import app.module.admin.usecase.exboard.save.ports.ExBoardSavePortOut;
import lombok.RequiredArgsConstructor;
import app.module.core.config.application.util.PropertyUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitExBoardData implements ApplicationRunner {
  private final ExBoardSavePortOut exBoardSavePortOut;
  private final RemoveExBoardAllRepo removeExBoardAllRepo;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    removeExBoardAllRepo.deleteAll();

    IntStream.range(0, 25)
        .forEach(
            idx ->
                exBoardSavePortOut.connect(
                    null,
                    String.format("Title No.%s", idx),
                    String.format("Content No.%s", idx),
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMonths(2),
                    null,
                    null,
                    null,
                    null,
                    idx));
  }
}
