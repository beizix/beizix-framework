package app.module.admin.usecase.article.getArticlesUI.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetArticlesUIReqVO {
  // 페이지 당 아이템 수
  private Integer size;
  // 정렬 값
  private String sort;
  // 요청 페이지 넘버
  private Integer page;
  private String searchField;
  private String searchValue;
  private String searchOpen;
}
