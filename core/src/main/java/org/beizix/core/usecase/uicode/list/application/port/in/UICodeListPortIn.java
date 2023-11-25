package org.beizix.core.usecase.uicode.list.application.port.in;

import org.beizix.core.usecase.uicode.list.domain.UICodeElement;

import java.util.List;

public interface UICodeListPortIn {
  List<UICodeElement> connect(String parentId);
}
