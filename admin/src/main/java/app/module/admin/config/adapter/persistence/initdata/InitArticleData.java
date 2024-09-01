package app.module.admin.config.adapter.persistence.initdata;

import app.module.admin.usecase.article.create.ports.CreateArticlePortIn;
import app.module.admin.usecase.article.create.ports.application.domain.CreateArticleCmd;
import app.module.core.config.application.util.PropertyUtil;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitArticleData implements ApplicationRunner {
  private final CreateArticlePortIn createArticlePortIn;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    IntStream.range(0, 25)
        .forEach(
            idx ->
                createArticlePortIn.operate(
                    new CreateArticleCmd(
                        String.format("Article No.%s", idx),
                        String.format("Content No.%s", idx),
                        true,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(2),
                        idx,
                        null)));
  }
}
