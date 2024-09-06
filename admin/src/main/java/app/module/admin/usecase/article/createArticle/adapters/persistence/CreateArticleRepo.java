package app.module.admin.usecase.article.createArticle.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreateArticleRepo extends JpaRepository<Article, Long> {
  @Query("select max(e.orderNo) from Article e")
  Optional<Integer> getMaxOrderNo();
}
