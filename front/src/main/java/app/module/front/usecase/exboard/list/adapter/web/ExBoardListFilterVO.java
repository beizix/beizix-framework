package app.module.front.usecase.exboard.list.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExBoardListFilterVO {

  private String searchField;
  private String searchValue;
  private String searchOpen;

  private Integer page;
  private Integer size;
  private String sort;

  private List<Long> checkedIds;
}
