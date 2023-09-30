package org.beizix.core.application.domain.exboard.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardListFilterInput {
  private String searchField;
  private String searchValue;
  private String searchOpen;

  private Integer page;
  private Integer size;
  private String sort;
}
