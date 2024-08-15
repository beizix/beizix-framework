package app.module.admin.usecase.role.save.application.port.out;

import java.util.List;

public interface RoleSavePortOut {
  String connect(String id, String description, Integer orderNo, List<String> privilegeIds);
}
