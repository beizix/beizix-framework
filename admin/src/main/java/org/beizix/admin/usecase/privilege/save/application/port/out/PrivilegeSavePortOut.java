package org.beizix.admin.usecase.privilege.save.application.port.out;

import org.beizix.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;

public interface PrivilegeSavePortOut {
  String connect(PrivilegeSaveCommand saveInput);
}
