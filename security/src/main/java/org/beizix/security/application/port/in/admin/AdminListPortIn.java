package org.beizix.security.application.port.in.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;
import org.beizix.security.application.domain.admin.model.filter.AdminListInput;

public interface AdminListPortIn {
  Page<AdminListOutput> connect(Pageable pageable, AdminListInput adminListInput);
}
