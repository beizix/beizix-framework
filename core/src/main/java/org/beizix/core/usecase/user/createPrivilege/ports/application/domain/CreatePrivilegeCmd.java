package org.beizix.core.usecase.user.createPrivilege.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CreatePrivilegeCmd {
  private String id;
  private String description;
  private Integer orderNo;
}
