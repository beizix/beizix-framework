package app.module.admin.usecase.uicode.save.application.port.out;

import app.module.admin.usecase.uicode.save.application.port.in.UICodeSaveCommand;

public interface UICodeSavePortOut {
  String connect(UICodeSaveCommand saveCommand);
}
