package org.beizix.security.application.port.in.admin;

import org.beizix.security.application.domain.admin.model.query.AdminListInput;

public interface AdminRemovePortIn {
  void connect(AdminListInput adminListInput);
}
