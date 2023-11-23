package org.beizix.admin.usecases.uicode.save.application.port.out;

import org.beizix.admin.usecases.uicode.save.application.port.in.UICodeSaveCommand;

public interface UICodeSavePortOut {
  String connect(UICodeSaveCommand saveCommand);
}
