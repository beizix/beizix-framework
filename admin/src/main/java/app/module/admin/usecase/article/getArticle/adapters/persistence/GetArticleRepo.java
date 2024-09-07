package app.module.admin.usecase.article.getArticle.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetArticleRepo extends JpaRepository<Article, Long> {}
