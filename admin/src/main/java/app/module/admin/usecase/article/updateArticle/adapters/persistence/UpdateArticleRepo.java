package app.module.admin.usecase.article.updateArticle.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateArticleRepo extends JpaRepository<Article, Long> {}
