package org.beizix.security.application.port.in.role;

import org.beizix.security.application.domain.role.model.save.RoleSaveInput;

public interface RoleSavePortIn {
  RoleSaveInput connect(RoleSaveInput saveReq);
}
