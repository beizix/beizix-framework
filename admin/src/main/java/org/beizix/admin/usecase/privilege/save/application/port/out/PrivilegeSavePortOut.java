package org.beizix.admin.usecase.privilege.save.application.port.out;

import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;

public interface PrivilegeSavePortOut {
  String connect(PrivilegeSaveInput saveInput);
}
