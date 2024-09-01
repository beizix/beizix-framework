package app.module.admin.usecase.article.getArticles.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GetArticlesRepo
    extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {}
