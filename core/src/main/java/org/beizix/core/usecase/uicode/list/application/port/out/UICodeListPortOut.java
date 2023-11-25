package org.beizix.core.usecase.uicode.list.application.port.out;

import org.beizix.core.usecase.uicode.list.domain.UICodeElement;

import java.util.List;

public interface UICodeListPortOut {
  List<UICodeElement> connect(String parentId);
}
