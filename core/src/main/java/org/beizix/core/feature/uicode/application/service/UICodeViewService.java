package org.beizix.core.feature.uicode.application.service;

import org.beizix.core.feature.uicode.application.model.UICode;

import java.util.Optional;

public interface UICodeViewService {
  Optional<UICode> operate(String id);
}
