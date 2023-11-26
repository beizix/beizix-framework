package org.beizix.admin.usecase.uicode.view.application.port.in;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uicode.view.application.domain.UICodeView;
import org.beizix.admin.usecase.uicode.view.application.port.out.UICodeViewPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UICodeViewService implements UICodeViewPortIn {
  private final UICodeViewPortOut uiCodeViewPortOut;

  @Override
  public Optional<UICodeView> connect(String id) {
    return Optional.ofNullable(uiCodeViewPortOut.connect(id));
  }
}
