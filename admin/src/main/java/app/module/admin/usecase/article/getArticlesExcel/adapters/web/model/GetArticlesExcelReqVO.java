package app.module.admin.usecase.article.getArticlesExcel.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetArticlesExcelReqVO {
  private String searchField;
  private String searchValue;
  private String searchOpen;
}
