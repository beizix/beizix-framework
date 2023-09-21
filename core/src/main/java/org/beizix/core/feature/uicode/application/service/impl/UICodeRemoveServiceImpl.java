package org.beizix.core.feature.uicode.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.uicode.application.service.UICodeRemoveService;
import org.beizix.core.feature.uicode.persistence.dao.UICodeRemoveDao;

@Service
@RequiredArgsConstructor
class UICodeRemoveServiceImpl implements UICodeRemoveService {
  private final UICodeRemoveDao uiCodeRemoveDao;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public void operate(String id) {
    uiCodeRemoveDao.operate(id);
  }
}
