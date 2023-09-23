package org.beizix.core.application.domain.uicode;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.application.domain.uicode.model.UICodeInput;
import org.beizix.core.application.port.in.uicode.UICodeListPortIn;
import org.beizix.core.application.port.out.uicode.UICodeListPortOut;

import java.util.List;

@Service(value = "uiCodeListService")
@RequiredArgsConstructor
class UICodeListService implements UICodeListPortIn {
  private final UICodeListPortOut uiCodeListPortOut;

  @Cacheable("ChildUICodesByParentIdCache")
  @Override
  public List<UICodeInput> connect(String parentId) {
    return uiCodeListPortOut.connect(parentId);
  }
}
