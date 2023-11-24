package org.beizix.admin.usecase.uicode.save.application.port.out;

import org.beizix.admin.usecase.uicode.save.application.port.in.UICodeSaveCommand;

public interface UICodeSavePortOut {
  String connect(UICodeSaveCommand saveCommand);
}
