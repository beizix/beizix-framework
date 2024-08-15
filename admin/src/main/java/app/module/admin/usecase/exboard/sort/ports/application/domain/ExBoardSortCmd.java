package app.module.admin.usecase.exboard.sort.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardSortCmd {
  private Long id;
  private Integer orderNo;
}
