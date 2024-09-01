package app.module.admin.config.adapter.persistence.initdata.helper;

import app.module.core.config.adapter.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveAllArticleRepo extends JpaRepository<Article, Long> {}
