package org.beizix.admin.usecase.privilege.save.application.port.in;

import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;

public interface PrivilegeSavePortIn {
  String connect(PrivilegeSaveInput saveInput);
}
