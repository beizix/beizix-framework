package org.beizix.security.application.port.in.admin_role;

import java.util.Set;

public interface AdminWithRoleRemovePortIn {
  void connect(Set<Long> ids);
}
