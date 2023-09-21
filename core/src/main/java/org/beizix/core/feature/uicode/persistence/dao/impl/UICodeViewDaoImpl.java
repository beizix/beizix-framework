package org.beizix.core.feature.uicode.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.persistence.dao.UICodeViewDao;
import org.beizix.core.feature.uicode.persistence.dao.impl.repository.UICodeViewRepo;
import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

@Repository
@RequiredArgsConstructor
class UICodeViewDaoImpl implements UICodeViewDao {
  private final UICodeViewRepo uiCodeViewRepo;

  @Override
  public UICode operate(String id) {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setAmbiguityIgnored(true);
    mapper
        .typeMap(UICodeEntity.class, UICode.class)
        .addMappings(mapping -> mapping.skip(UICode::setNodes));

    return uiCodeViewRepo
        .findById(id)
        .map(uiCodeEntity -> mapper.map(uiCodeEntity, UICode.class))
        .orElse(null);
  }
}
