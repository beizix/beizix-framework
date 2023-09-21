package org.beizix.core.feature.uri.persistence.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.persistence.dao.URIListDao;

@Repository
@RequiredArgsConstructor
class URIListDaoImpl implements URIListDao {
  private final URIListRepo uriListRepo;
  private final ModelMapper modelMapper;

  @Override
  public List<URI> operate(AppType appType) {
    return uriListRepo.findAllByAppType(appType).stream()
        .map(uriEntity -> modelMapper.map(uriEntity, URI.class))
        .collect(Collectors.toList());
  }
}
