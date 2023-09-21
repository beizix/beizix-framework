package org.beizix.security.application.port.in.admin;

import org.beizix.security.application.domain.admin.model.query.AdminListReq;

public interface AdminRemoveService {
  void operate(AdminListReq adminListReq);
}
