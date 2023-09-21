package org.beizix.core.feature.uicode.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.persistence.dao.UICodeCreateUpdateDao;
import org.beizix.core.feature.uicode.persistence.dao.impl.repository.UICodeCreateUpdateRepo;
import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

@Repository
@RequiredArgsConstructor
class UICodeCreateUpdateDaoImpl implements UICodeCreateUpdateDao {
  private final UICodeCreateUpdateRepo uiCodeCreateUpdateRepo;
  private final ModelMapper modelMapper;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public UICode operate(UICode uiCode) {
    UICodeEntity uiCodeEntity =
        uiCodeCreateUpdateRepo.save(modelMapper.map(uiCode, UICodeEntity.class));
    return modelMapper.map(uiCodeEntity, UICode.class);
  }
}
