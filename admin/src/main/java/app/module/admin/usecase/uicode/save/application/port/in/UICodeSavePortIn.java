package app.module.admin.usecase.uicode.save.application.port.in;

public interface UICodeSavePortIn {
  String connect(UICodeSaveCommand createCommand, boolean isUpdate);
}
