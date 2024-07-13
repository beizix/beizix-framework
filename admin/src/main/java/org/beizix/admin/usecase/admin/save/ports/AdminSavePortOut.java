package org.beizix.admin.usecase.admin.save.ports;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminSavePortOut {
  String connect(
      String id,
      String encodedPassword,
      LocalDateTime passwordUpdatedAt,
      String email,
      Boolean accountDisabled,
      Integer loginFailCnt,
      Boolean accountLocked,
      List<String> roleIds);
}
