package org.beizix.security.application.domain.role.model.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WithPrivilegeSaveReq {
  private Integer id;

  private RoleSaveReq role;
  private PrivilegeSaveReq privilegeDto;
}
