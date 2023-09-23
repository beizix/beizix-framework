package org.beizix.core.application.port.in.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeSortInput;

import java.util.List;

public interface UICodeSortPortIn {
  void connect(List<UICodeSortInput> uiCodeSortInputs);
}
