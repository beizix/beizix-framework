package org.beizix.admin.usecase.uicode.remove.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uicode.remove.application.port.out.UICodeRemovePortOut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

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
