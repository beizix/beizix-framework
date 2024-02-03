package org.beizix.admin.usecase.admin.list.application.port.out;

import org.beizix.admin.usecase.admin.list.application.domain.AdminElement;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminListPortOut {
  Page<AdminElement> connect(Pageable pageable, AdminListFilterCommand filterCommand);
}
