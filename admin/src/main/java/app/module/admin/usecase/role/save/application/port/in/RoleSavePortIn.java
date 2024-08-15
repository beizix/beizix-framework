package app.module.admin.usecase.role.save.application.port.in;

import java.util.List;

public interface RoleSavePortIn {
  String connect(String id, String description, Integer orderNo, List<String> privilegeIds);
}
