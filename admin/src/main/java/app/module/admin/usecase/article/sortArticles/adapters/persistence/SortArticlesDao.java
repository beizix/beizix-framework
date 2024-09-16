package app.module.admin.usecase.article.sortArticles.adapters.persistence;

import app.module.admin.usecase.article.sortArticles.ports.SortArticlesPortOut;
import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;
import app.module.core.config.adapter.persistence.entity.Article;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class SortArticlesDao implements SortArticlesPortOut {
  private final SortArticlesRepo sortArticlesRepo;

  @Override
  @Transactional
  public void operate(List<SortArticlesCmd> command) {
    // 변경 감지(dirty checking)를 이용한 업데이트.
    // Transaction 커밋 시점에 변경된 상태를 감지해 JPA 가 자동으로 업데이트를 수행한다.
    List<Article> articles =
        sortArticlesRepo.findAllById(
            command.stream()
                .map(sortCmd -> sortCmd.getId())
                .collect(Collectors.toList()));

    articles.forEach(
        article ->
            article.setOrderNo(
                command.stream()
                    .filter(sortCmd -> sortCmd.getId().equals(article.getId()))
                    .map(sortCmd -> sortCmd.getOrderNo())
                    .findFirst()
                    .orElseThrow()));
  }
}
