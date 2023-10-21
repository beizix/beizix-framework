package org.beizix.security.application.port.out.role;

import java.util.List;

public interface RoleSavePortOut {
  String connect(String id, String description, Integer orderNo, List<String> privilegeIds);
}
