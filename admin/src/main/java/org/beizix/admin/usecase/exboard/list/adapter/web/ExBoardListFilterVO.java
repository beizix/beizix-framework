package org.beizix.admin.usecase.exboard.list.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardListFilterVO {
  private String searchField;
  private String searchValue;
  private String searchOpen;
}
