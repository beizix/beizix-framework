package org.beizix.core.application.port.out.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;

public interface UICodeSavePortOut {
  UICodeInput connect(UICodeInput uiCodeInput);
}
