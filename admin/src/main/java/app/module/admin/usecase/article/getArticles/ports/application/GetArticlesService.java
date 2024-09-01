package app.module.admin.usecase.article.getArticles.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticlesCmd;
import app.module.admin.usecase.article.getArticles.ports.GetArticlesPortIn;
import app.module.admin.usecase.article.getArticles.ports.GetArticlesPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetArticlesService implements GetArticlesPortIn {
  private final GetArticlesPortOut portOut;

  @Override
  public Page<GetArticles> operate(Pageable pageable, GetArticlesCmd command) {
    return portOut.operate(pageable, command);
  }
}
