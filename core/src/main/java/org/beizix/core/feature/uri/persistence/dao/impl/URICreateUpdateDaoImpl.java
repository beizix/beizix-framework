package org.beizix.core.feature.uri.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.persistence.dao.URICreateUpdateDao;
import org.beizix.core.feature.uri.persistence.model.URIEntity;

@Repository
@RequiredArgsConstructor
public class URICreateUpdateDaoImpl implements URICreateUpdateDao {
  private final URIRepo uriRepo;
  private final ModelMapper modelMapper;

  @Override
  public URI operate(URI uri) {
    URIEntity createdItem = uriRepo.save(modelMapper.map(uri, URIEntity.class));
    return modelMapper.map(createdItem, URI.class);
  }
}
