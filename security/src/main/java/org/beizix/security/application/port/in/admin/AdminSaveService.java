package org.beizix.security.application.port.in.admin;

import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;

public interface AdminSaveService {
  AdminSaveReq operate(AdminSaveReq saveReq);

  void updateLoginFailCnt(String id, Integer failCnt);

  void updateAccountLocked(String id, boolean accountLocked);
}
