package org.beizix.core.feature.uicode.persistence.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.persistence.dao.UICodeListDao;
import org.beizix.core.feature.uicode.persistence.dao.impl.repository.UICodeListRepo;
import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

@Repository
@RequiredArgsConstructor
class UICodeListDaoImpl implements UICodeListDao {
  private final UICodeListRepo uiCodeListRepo;

  @Override
  public List<UICode> operate(String parentId) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    modelMapper
        .typeMap(UICodeEntity.class, UICode.class)
        .addMappings(mapper -> mapper.skip(UICode::setNodes));

    return uiCodeListRepo.operate(parentId).stream()
        .map(item -> modelMapper.map(item, UICode.class))
        .collect(Collectors.toList());
  }
}
