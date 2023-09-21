package org.beizix.front.feature.exboard.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExBoardListConditionDto {

  private String searchField;
  private String searchValue;
  private String searchOpen;

  private Integer page;
  private Integer size;
  private String sort;

  private List<Long> checkedIds;
}
