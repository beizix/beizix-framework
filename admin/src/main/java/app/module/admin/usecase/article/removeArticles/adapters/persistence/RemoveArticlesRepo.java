package app.module.admin.usecase.article.removeArticles.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveArticlesRepo extends JpaRepository<Article, Long> {}
