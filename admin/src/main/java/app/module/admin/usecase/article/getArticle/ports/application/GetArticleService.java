package app.module.admin.usecase.article.getArticle.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticle;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticleCmd;
import app.module.admin.usecase.article.getArticle.ports.GetArticlePortIn;
import app.module.admin.usecase.article.getArticle.ports.GetArticlePortOut;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetArticleService implements GetArticlePortIn {
  private final GetArticlePortOut portOut;

  @Override
  public Optional<GetArticle> operate(GetArticleCmd command) {
    return portOut.operate(command);
  }
}