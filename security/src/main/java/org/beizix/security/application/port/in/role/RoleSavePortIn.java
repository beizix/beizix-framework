package org.beizix.security.application.port.in.role;

import java.util.List;

public interface RoleSavePortIn {
  String connect(String id, String description, Integer orderNo, List<String> privilegeIds);
}
