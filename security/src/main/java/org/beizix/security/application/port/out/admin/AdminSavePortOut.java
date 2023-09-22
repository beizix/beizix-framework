package org.beizix.security.application.port.out.admin;

import java.util.Optional;
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;

public interface AdminSavePortOut {
  Optional<AdminSaveInput> connect(AdminSaveInput adminDto);

  void updateLoginFailCnt(String id, Integer failCnt);

  void updateAccountLocked(String id, boolean accountLocked);
}
