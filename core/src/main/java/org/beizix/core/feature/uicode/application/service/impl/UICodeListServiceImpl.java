package org.beizix.core.feature.uicode.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.application.service.UICodeListService;
import org.beizix.core.feature.uicode.persistence.dao.UICodeListDao;

import java.util.List;

@Service(value = "uiCodeListService")
@RequiredArgsConstructor
class UICodeListServiceImpl implements UICodeListService {
  private final UICodeListDao uiCodeListDao;

  @Cacheable("ChildUICodesByParentIdCache")
  @Override
  public List<UICode> operate(String parentId) {
    return uiCodeListDao.operate(parentId);
  }
}
