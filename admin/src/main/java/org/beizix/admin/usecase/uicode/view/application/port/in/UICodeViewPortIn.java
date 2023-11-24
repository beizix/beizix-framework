package org.beizix.admin.usecase.uicode.view.application.port.in;

import java.util.Optional;
import org.beizix.admin.usecase.uicode.view.domain.UICodeView;

public interface UICodeViewPortIn {
  Optional<UICodeView> connect(String id);
}
