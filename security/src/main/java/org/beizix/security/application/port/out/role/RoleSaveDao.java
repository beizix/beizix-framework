package org.beizix.security.application.port.out.role;

import org.beizix.security.application.domain.role.model.save.RoleSaveReq;

public interface RoleSaveDao {
  RoleSaveReq operate(RoleSaveReq saveReq);
}
