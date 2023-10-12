package org.beizix.security.application.port.in.admin;

import java.util.List;

public interface AdminSavePortIn {
  String connect(String id,
      String password,
      String email,
      Boolean accountDisabled,
      Boolean accountLocked,
      List<String> roleIds);
}
