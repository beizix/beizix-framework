package app.module.admin.usecase.admin.save.ports;

import java.util.List;

public interface AdminSavePortIn {
  String connect(String id,
      String password,
      String email,
      Boolean accountDisabled,
      Boolean accountLocked,
      List<String> roleIds);
}
