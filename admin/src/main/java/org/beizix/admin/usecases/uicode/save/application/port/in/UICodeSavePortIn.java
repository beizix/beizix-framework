package org.beizix.admin.usecases.uicode.save.application.port.in;

import org.beizix.core.application.domain.uicode.model.UICodeInput;

public interface UICodeSavePortIn {
  UICodeInput connect(UICodeInput uiCodeInput, boolean checkDuplicate);
}
