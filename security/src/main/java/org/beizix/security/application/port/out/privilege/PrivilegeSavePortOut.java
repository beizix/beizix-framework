package org.beizix.security.application.port.out.privilege;

import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;

public interface PrivilegeSavePortOut {
  String connect(PrivilegeSaveInput saveInput);
}
