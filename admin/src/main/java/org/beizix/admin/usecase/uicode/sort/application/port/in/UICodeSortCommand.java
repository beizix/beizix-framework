package org.beizix.admin.usecase.uicode.sort.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UICodeSortCommand {
  private String id;
  private Integer orderNo;
}
