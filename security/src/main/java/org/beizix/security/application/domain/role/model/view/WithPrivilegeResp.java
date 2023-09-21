package org.beizix.security.application.domain.role.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WithPrivilegeResp {
  private Integer id;
  private PrivilegeResp privilegeDto;
}
