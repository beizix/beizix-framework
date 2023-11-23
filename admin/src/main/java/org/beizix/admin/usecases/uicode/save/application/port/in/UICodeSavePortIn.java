package org.beizix.admin.usecases.uicode.save.application.port.in;

public interface UICodeSavePortIn {
  String connect(UICodeSaveCommand createCommand, boolean isUpdate);
}
