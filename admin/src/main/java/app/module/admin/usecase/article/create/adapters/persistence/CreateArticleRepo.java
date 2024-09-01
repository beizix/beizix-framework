package app.module.admin.usecase.article.create.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateArticleRepo extends JpaRepository<Article, Long> {}
