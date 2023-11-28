package org.beizix.admin.usecase.privilege.sort.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeSortCommand {
  private String id;
  private Integer orderNo;
}
