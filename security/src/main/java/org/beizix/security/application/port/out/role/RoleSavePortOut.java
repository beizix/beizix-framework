package org.beizix.security.application.port.out.role;

import org.beizix.security.application.domain.role.model.save.RoleSaveInput;

public interface RoleSavePortOut {
  RoleSaveInput connect(RoleSaveInput saveReq);
}
