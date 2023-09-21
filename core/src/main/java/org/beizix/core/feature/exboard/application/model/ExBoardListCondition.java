package org.beizix.core.feature.exboard.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardListCondition {
  private String searchField;
  private String searchValue;
  private String searchOpen;

  private Integer page;
  private Integer size;
  private String sort;
}
