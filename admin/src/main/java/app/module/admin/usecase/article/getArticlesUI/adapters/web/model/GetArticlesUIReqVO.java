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
  // 요청 페이지 넘버 - 초기값 `0`
  private Integer page = 0;
  // 정렬 값 - 초기값 `orderNo 내림차순`
  private String sort = "orderNo,DESC";
  // 페이지 당 아이템 수 - 초기값 `10`
  private Integer size = 10;
  private String searchField;
  private String searchValue;
  private String searchOpen;
}
