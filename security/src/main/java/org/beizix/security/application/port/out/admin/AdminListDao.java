package org.beizix.security.application.port.out.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.security.application.domain.admin.model.list.AdminListResp;
import org.beizix.security.application.domain.admin.model.query.AdminListReq;

public interface AdminListDao {
  Page<AdminListResp> operate(Pageable pageable, AdminListReq adminListReq);
}
