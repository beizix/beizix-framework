package org.beizix.security.application.port.out.admin_role;

import java.util.Set;

public interface AdminWithRolePortOut {
  void connect(Set<Long> ids);
}
