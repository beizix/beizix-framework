package org.beizix.admin.adapter.web.exboard.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class ExBoardListFilterReqVO {
  private String searchField;
  private String searchValue;
  private String searchOpen;
  private Integer page;
  private Integer size;
  private String sort;
  private List<Long> selectedItemIds;
}
