package org.beizix.core.application.domain.uicode;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.application.port.in.uicode.UICodeSortPortIn;
import org.beizix.core.application.domain.uicode.model.UICodeSortInput;
import org.beizix.core.application.port.out.uicode.UICodeSortPortOut;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
class UICodeSortPortService implements UICodeSortPortIn {
  private final UICodeSortPortOut uiCodeSortPortOut;

  @Transactional
  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public void connect(List<UICodeSortInput> uiCodeSortInput) {
    uiCodeSortInput.forEach(item -> uiCodeSortPortOut.connect(item.getId(), item.getOrderNo()));
  }
}
