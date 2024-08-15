package app.module.core.usecase.uicode.list.application.port.in;

import app.module.core.usecase.uicode.list.application.domain.UICodeElement;

import java.util.List;

public interface UICodeListPortIn {
  List<UICodeElement> connect(String parentId);
}
