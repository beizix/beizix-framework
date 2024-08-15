package app.module.admin.usecase.uicode.view.application.port.in;

import java.util.Optional;

import app.module.admin.usecase.uicode.view.application.domain.UICodeView;

public interface UICodeViewPortIn {
  Optional<UICodeView> connect(String id);
}
