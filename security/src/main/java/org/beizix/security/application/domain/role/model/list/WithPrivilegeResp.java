package org.beizix.security.application.domain.role.model.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class WithPrivilegeResp {
  private Integer id;
  private PrivilegeResp privilegeDto;
}
