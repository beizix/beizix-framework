package app.module.admin.usecase.article.sortArticles.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SortArticlesRepo extends JpaRepository<Article, Long> {
  @Modifying
  @Query("update Article e set e.orderNo = :orderNo where e.id = :id")
  void operate(@Param("id") Long id, @Param("orderNo") Integer orderNo);
}
