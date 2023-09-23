package org.beizix.core.application.port.out.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;

import java.util.List;

public interface UICodeListPortOut {
  List<UICodeInput> connect(String parentId);
}
