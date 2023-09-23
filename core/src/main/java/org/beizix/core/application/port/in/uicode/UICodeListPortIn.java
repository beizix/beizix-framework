package org.beizix.core.application.port.in.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;

import java.util.List;

public interface UICodeListPortIn {
  List<UICodeInput> connect(String parentId);
}
