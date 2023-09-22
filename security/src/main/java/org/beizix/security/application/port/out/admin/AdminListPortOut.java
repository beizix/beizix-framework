package org.beizix.security.application.port.out.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;
import org.beizix.security.application.domain.admin.model.query.AdminListInput;

public interface AdminListPortOut {
  Page<AdminListOutput> connect(Pageable pageable, AdminListInput inflow);
}
