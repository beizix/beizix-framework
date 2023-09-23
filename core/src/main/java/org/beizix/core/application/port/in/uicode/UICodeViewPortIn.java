package org.beizix.core.application.port.in.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;

import java.util.Optional;

public interface UICodeViewPortIn {
  Optional<UICodeInput> connect(String id);
}
