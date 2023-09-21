package org.beizix.security.application.port.out.admin_role;

import java.util.Set;

public interface AdminWithRoleRemoveDao {
  void operate(Set<Long> ids);
}
