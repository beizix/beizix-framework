package org.beizix.security.application.port.in.admin;

import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;

public interface AdminSavePortIn {
  AdminSaveInput connect(AdminSaveInput saveReq);

  void updateLoginFailCnt(String id, Integer failCnt);

  void updateAccountLocked(String id, boolean accountLocked);
}
