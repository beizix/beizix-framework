package app.module.admin.usecase.user.getUsersUI.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.util.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetUsersUIReqVO {
  private Integer page;
  private String sort;
  private Integer size;
  private final String searchField;
  private final String searchValue;
  private final String searchRole;

  public Integer getPage() {
    // 요청 페이지 넘버 - 초기값 `0`
    return page != null ? page : 0;
  }

  public String getSort() {
    // 정렬 값 - 초기값 `createdAt 내림차순`
    return StringUtils.isEmpty(sort) ? "createdAt,DESC" : sort;
  }

  public Integer getSize() {
    // 페이지 당 아이템 수 - 초기값 `10`
    return size != null ? size : 10;
  }
}
