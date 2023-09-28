package org.beizix.admin.adapter.web.exboard.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExBoardListFilterReqVO {
  private String searchField;
  private String searchValue;
  private String searchOpen;
  private Integer page;
  private Integer size;
  private String sort;
  private List<Long> itemIds;
}
