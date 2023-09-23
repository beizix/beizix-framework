package org.beizix.core.application.domain.uicode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.application.port.in.uicode.UICodeViewPortIn;
import org.beizix.core.application.port.out.uicode.UICodeViewPortOut;
import org.beizix.core.application.domain.uicode.model.UICodeInput;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class UICodeViewService implements UICodeViewPortIn {
  private final UICodeViewPortOut uiCodeViewPortOut;

  @Override
  public Optional<UICodeInput> connect(String id) {
    return Optional.ofNullable(uiCodeViewPortOut.connect(id));
  }
}
