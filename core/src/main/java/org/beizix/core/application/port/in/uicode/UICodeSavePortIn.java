package org.beizix.core.application.port.in.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;

public interface UICodeSavePortIn {
  UICodeInput connect(UICodeInput uiCodeInput, boolean checkDuplicate);
}
