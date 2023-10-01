package org.beizix.admin.adapter.web.exboard.model.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardListFilterReqVO {
  private String searchField;
  private String searchValue;
  private String searchOpen;
}
