package org.beizix.core.feature.uicode.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.uicode.application.service.UICodeSortService;
import org.beizix.core.feature.uicode.application.model.UICodeSort;
import org.beizix.core.feature.uicode.persistence.dao.UICodeSortDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
class UICodeSortServiceImpl implements UICodeSortService {
  private final UICodeSortDao uiCodeSortDao;

  @Transactional
  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public void operate(List<UICodeSort> uiCodeSort) {
    uiCodeSort.forEach(item -> uiCodeSortDao.operate(item.getId(), item.getOrderNo()));
  }
}
