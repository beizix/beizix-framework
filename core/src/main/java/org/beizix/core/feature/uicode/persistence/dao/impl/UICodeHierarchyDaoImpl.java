package org.beizix.core.feature.uicode.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.persistence.dao.UICodeHierarchyDao;
import org.beizix.core.feature.uicode.persistence.dao.impl.repository.UICodeHierarchyRepo;
import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

@Repository
@RequiredArgsConstructor
class UICodeHierarchyDaoImpl implements UICodeHierarchyDao {
  private final UICodeHierarchyRepo uiCodeHierarchyRepo;
  private final ModelMapper modelMapper;

  @Override
  public UICode operate() {
    UICodeEntity topNode = uiCodeHierarchyRepo.findByParentIdIsNull();
    return modelMapper.map(topNode, UICode.class);
  }
}
