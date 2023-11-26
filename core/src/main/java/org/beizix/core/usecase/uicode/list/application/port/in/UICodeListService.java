package org.beizix.core.usecase.uicode.list.application.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.usecase.uicode.list.application.domain.UICodeElement;
import org.beizix.core.usecase.uicode.list.application.port.out.UICodeListPortOut;

import java.util.List;

@Service
@RequiredArgsConstructor
class UICodeListService implements UICodeListPortIn {
  private final UICodeListPortOut uiCodeListPortOut;

  @Cacheable("ChildUICodesByParentIdCache")
  @Override
  public List<UICodeElement> connect(String parentId) {
    return uiCodeListPortOut.connect(parentId);
  }
}
