package org.beizix.security.application.port.out.admin;

import org.beizix.security.application.domain.admin.model.query.AdminListReq;

public interface AdminRemoveDao {
  void operate(AdminListReq adminListReq);
}
