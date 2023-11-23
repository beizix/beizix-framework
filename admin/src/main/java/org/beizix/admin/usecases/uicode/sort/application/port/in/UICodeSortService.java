package org.beizix.admin.usecases.uicode.sort.application.port.in;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uicode.sort.application.port.out.UICodeSortPortOut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UICodeSortService implements UICodeSortPortIn {
  private final UICodeSortPortOut uiCodeSortPortOut;

  @Transactional
  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public void connect(List<UICodeSortCommand> sortCommands) {
    sortCommands.forEach(item -> uiCodeSortPortOut.connect(item.getId(), item.getOrderNo()));
  }
}
