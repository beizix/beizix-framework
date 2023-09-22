package org.beizix.security.application.domain.role.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WithPrivilegeOutput {
  private Integer id;
  private PrivilegeOutput privilegeDto;
}
