package org.beizix.admin.usecase.privilege.sort.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeSortCmd {
  private String id;
  private Integer orderNo;
}
