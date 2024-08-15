package app.module.admin.usecase.privilege.save.ports;

import app.module.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;

public interface PrivilegeSavePortOut {
  String connect(PrivilegeSaveCommand saveInput);
}
