package app.module.core.usecase.uicode.list.application.port.out;

import app.module.core.usecase.uicode.list.application.domain.UICodeElement;

import java.util.List;

public interface UICodeListPortOut {
  List<UICodeElement> connect(String parentId);
}
