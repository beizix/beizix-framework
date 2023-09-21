package org.beizix.security.application.port.in.role;

import org.beizix.security.application.domain.role.model.save.RoleSaveReq;

public interface RoleSaveService {
  RoleSaveReq operate(RoleSaveReq saveReq);
}
