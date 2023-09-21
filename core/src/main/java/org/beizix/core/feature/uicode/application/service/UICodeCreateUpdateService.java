package org.beizix.core.feature.uicode.application.service;

import org.beizix.core.feature.uicode.application.model.UICode;

public interface UICodeCreateUpdateService {
  UICode operate(UICode uiCode, boolean checkDuplicate);
}
