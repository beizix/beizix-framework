package org.beizix.admin.usecase.admin.list.ports;

import org.beizix.admin.usecase.admin.list.ports.application.domain.AdminElement;
import org.beizix.admin.usecase.admin.list.ports.application.domain.AdminListCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminListPortIn {
  Page<AdminElement> connect(Pageable pageable, AdminListCmd filterCommand);
}
