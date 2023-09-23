package org.beizix.security.application.port.out.admin;

import org.beizix.security.application.domain.admin.model.filter.AdminListInput;

public interface AdminRemovePortOut {
  void connect(AdminListInput adminListInput);
}
