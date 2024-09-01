package app.module.admin.usecase.article.getArticles.ports;

import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticlesCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetArticlesPortIn {
  Page<GetArticles> operate(Pageable pageable, GetArticlesCmd command);
}