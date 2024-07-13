package org.beizix.admin.usecase.privilege.save.application.port.in;

import org.beizix.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;

public interface PrivilegeSavePortIn {
  String connect(PrivilegeSaveCommand saveInput);
}
