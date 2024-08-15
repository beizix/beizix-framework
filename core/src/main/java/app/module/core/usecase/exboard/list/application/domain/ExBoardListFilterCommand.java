package app.module.core.usecase.exboard.list.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardListFilterCommand {
  private String searchField;
  private String searchValue;
  private String searchOpen;
}
