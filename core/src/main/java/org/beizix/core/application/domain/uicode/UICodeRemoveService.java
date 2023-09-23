package org.beizix.core.application.domain.uicode;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.application.port.in.uicode.UICodeRemovePortIn;
import org.beizix.core.application.port.out.uicode.UICodeRemovePortOut;

@Service
@RequiredArgsConstructor
class UICodeRemoveService implements UICodeRemovePortIn {
  private final UICodeRemovePortOut uiCodeRemovePortOut;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public void operate(String id) {
    uiCodeRemovePortOut.connect(id);
  }
}
