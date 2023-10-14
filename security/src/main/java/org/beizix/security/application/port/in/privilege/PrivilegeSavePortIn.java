package org.beizix.security.application.port.in.privilege;

import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;

public interface PrivilegeSavePortIn {
  String connect(PrivilegeSaveInput saveInput);
}
