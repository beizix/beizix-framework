package app.module.admin.usecase.privilege.save.ports;

import app.module.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;

public interface PrivilegeSavePortIn {
  String connect(PrivilegeSaveCommand saveInput);
}
