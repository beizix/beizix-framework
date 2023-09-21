package org.beizix.security.application.port.in.admin_role;

import java.util.Set;

public interface AdminWithRoleRemoveService {
  void operateAll(Set<Long> ids);
}
