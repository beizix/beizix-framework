package org.beizix.core.application.domain.exboard.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardListFilterInput {
  private String searchField;
  private String searchValue;
  private String searchOpen;
}
