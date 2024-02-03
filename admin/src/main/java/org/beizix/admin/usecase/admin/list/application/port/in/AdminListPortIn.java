package org.beizix.admin.usecase.admin.list.application.port.in;

import org.beizix.admin.usecase.admin.list.application.domain.AdminElement;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminListPortIn {
  Page<AdminElement> connect(Pageable pageable, AdminListFilterCommand filterCommand);
}
