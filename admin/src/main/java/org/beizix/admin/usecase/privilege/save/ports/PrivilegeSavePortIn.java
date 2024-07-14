package org.beizix.admin.usecase.privilege.save.ports;

import org.beizix.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;

public interface PrivilegeSavePortIn {
  String connect(PrivilegeSaveCommand saveInput);
}
