package org.beizix.admin.usecase.exboard.sort.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardSortCommand {
  private Long id;
  private Integer orderNo;
}
